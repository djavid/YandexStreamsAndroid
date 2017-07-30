package net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.implementations;

import net.azurewebsites.streambeta.yandexstreamsandroid.core.BasePresenter;
import net.azurewebsites.streambeta.yandexstreamsandroid.core.view.DonateFragmentView;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.interactor.DonateFragmentInteractor;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.interactor.DonateFragmentUseCase;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.StreamSettingsDto;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.instancestate.DonateFragmentPresenterInstanceState;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.interfaces.DonateFragmentPresenter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.router.MainRouter;
import net.azurewebsites.streambeta.yandexstreamsandroid.util.RxUtils;
import net.azurewebsites.streambeta.yandexstreamsandroid.util.ThrowableToStringIdConverter;

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

            if (!disposable.isDisposed())
                disposable.dispose();

            disposable = donateFragmentInteractor.getStreamSettings(stream_id)
                    .compose(RxUtils.applySingleSchedulers())
                    .retry(2l)
                    .subscribe(
                            (model) -> {
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
}
