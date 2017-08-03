package net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.implementations;

import android.app.AlertDialog;
import android.content.DialogInterface;

import com.yandex.money.api.methods.payment.BaseProcessPayment;
import com.yandex.money.api.methods.payment.BaseRequestPayment;
import com.yandex.money.api.methods.payment.ProcessPayment;
import com.yandex.money.api.methods.payment.RequestPayment;
import com.yandex.money.api.methods.payment.params.P2pTransferParams;
import com.yandex.money.api.methods.payment.params.PaymentParams;
import com.yandex.money.api.net.clients.ApiClient;

import net.azurewebsites.streambeta.yandexstreamsandroid.core.BasePresenter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.interfaces.DonateFragmentView;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.App;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.interactor.DonateFragmentInteractor;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.interactor.DonateFragmentUseCase;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.DonationDto;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.StreamSettingsDto;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.instancestate.DonateFragmentPresenterInstanceState;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.interfaces.DonateFragmentPresenter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.router.MainRouter;
import net.azurewebsites.streambeta.yandexstreamsandroid.util.RxUtils;
import net.azurewebsites.streambeta.yandexstreamsandroid.util.ThrowableToStringIdConverter;

import java.math.BigDecimal;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

/**
 * Created by djavid on 30.07.17.
 */


public class DonateFragmentPresenterImpl
        extends BasePresenter<DonateFragmentView, MainRouter, DonateFragmentPresenterInstanceState>
        implements DonateFragmentPresenter {

    private Disposable disposable = Disposables.empty();

    private DonateFragmentInteractor donateFragmentInteractor;
    private StreamSettingsDto streamSettingsDto;


    public DonateFragmentPresenterImpl() {
        donateFragmentInteractor = new DonateFragmentUseCase();
    }

    @Override
    public String getId() {
        return "donate_fragment";
    }

    @Override
    public void loadStreamSettings(int stream_id) {
        if (getView() != null) {
            getView().showProgressbar();
        }

        if (!disposable.isDisposed())
            disposable.dispose();

        disposable = donateFragmentInteractor.getStreamSettings(stream_id)
                .compose(RxUtils.applySingleSchedulers())
                .retry(2l)
                .subscribe(model -> {
                            streamSettingsDto = model;

                            if (getView() != null) {
                                getView().setStreamSettings(model);
                                getView().hideProgressbar();
                            } else {
                                setInstanceState(new DonateFragmentPresenterInstanceState(model));
                            }
                        },
                        (Throwable) -> {
                            getView().showError(ThrowableToStringIdConverter.convert(Throwable));
                        });

    }

    @Override
    public void requestPayment() {
        if (getView() != null) {
            getView().showProgressbar();
        }

        ApiClient apiClient = App.getAppInstance().getApiClient();

        System.out.println(getView().getStreamerId());
        PaymentParams paymentParams = new P2pTransferParams.Builder(getView().getStreamerId())
                .setAmountDue(new BigDecimal((double) (getView().getSumText() / 100)))
                .setLabel("yms")
                .create();

//        apiClient.setAccessToken(App.getAppInstance().getPreferencesWrapper()
//                .getAuthToken("yandex_money"));
//        System.out.println(App.getAppInstance().getPreferencesWrapper()
//                .getAuthToken("yandex_money"));

        AlertDialog.Builder alert = getView().createDonateAlertDialog();
        alert.setPositiveButton("Отправить", (dialog, which) -> {
            try {

                Observable.fromCallable(() -> apiClient
                        .execute(RequestPayment.Request.newInstance(paymentParams)))
                        .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(response -> {
                                    getView().hideProgressbar();

                                    if (response.error != null) {
                                        System.out.println(response.error.toString());
                                    } else {
                                        System.out.println(response.toString());
                                        System.out.println("moneySource: " + response.moneySource.toString());
                                        System.out.println("balance: " + response.balance);

                                        if (response.status == BaseRequestPayment.Status.SUCCESS) {
                                            processPayment(response);
                                        }
                                    }

                                }, error -> {
                                    if (getView() != null) {
                                        getView().showError(ThrowableToStringIdConverter.convert(error));
                                    }
                                }
                        );


            } catch (Exception e) {
                if (getView() != null) {
                    getView().hideProgressbar();
                }
            }
        });
        alert.setNegativeButton("Отменить", (dialog, which) -> getView().hideProgressbar());


        alert.show();

    }

    @Override
    public void processPayment(RequestPayment requestPayment) {
        getView().showProgressbar();

        ApiClient apiClient = App.getAppInstance().getApiClient();

//        apiClient.setAccessToken(App.getAppInstance().getPreferencesWrapper()
//                .getAuthToken("yandex_money"));

        Single.fromCallable(() -> apiClient
                .execute(new ProcessPayment.Request(requestPayment.requestId, null, "", "", "")))
                .compose(RxUtils.applySingleSchedulers())
                .subscribe(response -> {
                            getView().hideProgressbar();

                            if (response.error != null) {
                                System.out.println("ProcessPayment: " + response.error.toString());

                                AlertDialog.Builder alertDialog = getView().createDonateAlertDialog();
                                alertDialog.setTitle("Оплата отменена");
                                alertDialog.setMessage(response.error.toString());
                                alertDialog.setPositiveButton("Закрыть", (dialog, which) -> {
                                    dialog.cancel();
                                });
                                alertDialog.show();

                            } else {
                                System.out.println(response.toString());

                                if (response.status == BaseProcessPayment.Status.SUCCESS) {
                                    //send donation
                                    if (getView() != null) {
                                        DonationDto donationDto = new DonationDto();
                                        donationDto.setOperation_id(response.paymentId);
                                        donationDto.setStream_id(streamSettingsDto.getStreamId());
                                        donationDto.setStreamer_id(getView().getStreamerId());
                                        donationDto.setType("text");
                                        donationDto.setText_data(getView().getDonateText());
                                        donationDto.setAmount(getView().getSumText());
                                        donationDto.setSender(getView().getNicknameText());
                                        donationDto.setUser_id(App.getAppInstance().getPreferencesWrapper()
                                                .getDeviceId("device_id"));

                                        sendDonationWithId(donationDto);
                                    }
                                }
                            }
                        }, error -> {
                            if (getView() != null) {
                                getView().showError(ThrowableToStringIdConverter.convert(error));
                            }
                        }
                );

    }

    @Override
    public void sendDonationWithId(DonationDto donationDto) {
        getView().showProgressbar();

        donateFragmentInteractor.sendDonationWithId(donationDto)
                .compose(RxUtils.applyCompletableSchedulers())
                .subscribe(() -> {
                    getView().hideProgressbar();
                    //TODO show alert about success

                    AlertDialog.Builder alert = getView().createDonateAlertDialog();
                    alert.setTitle("");
                    alert.setMessage("Донат успешно отправлен!");
                    alert.setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    alert.show();

                }, error -> {
                    if (getView() != null) {
                        getView().hideProgressbar();
                        getView().showError(ThrowableToStringIdConverter.convert(error));
                    }
                });
    }

    @Override
    public boolean isAuthorised() {
        return !App.getAppInstance().getPreferencesWrapper().getAuthToken("yandex_money").isEmpty();
    }

    @Override
    public boolean checkForm() {
        if (getView() != null) {
            if (getView().getDonateText().length() <= streamSettingsDto.getTextL()
                    && getView().getSumText() >= streamSettingsDto.getMinSum()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void onStart() {
        if (getInstanceState() != null) {
            if (getInstanceState().getStreamSettingsDto() == null) {
                getView().setNicknameText(getInstanceState().getNick_name());
                getView().setSumText(getInstanceState().getDonate_sum());
                getView().setDonateText(getInstanceState().getDonate_text());
            } else {
                getView().setStreamSettings(getInstanceState().getStreamSettingsDto());
            }
        }
    }

    @Override
    public void onStop() {

    }

    @Override
    public void saveInstanceState(DonateFragmentPresenterInstanceState instanceState) {
        setInstanceState(instanceState);
    }

    @Override
    public StreamSettingsDto getStreamSettings() {
        return streamSettingsDto;
    }

}
