package net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.implementations;


import com.yandex.money.api.exceptions.InvalidTokenException;
import com.yandex.money.api.methods.Token;
import com.yandex.money.api.methods.wallet.AccountInfo;

import net.azurewebsites.streambeta.yandexstreamsandroid.core.BasePresenter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.App;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.interactor.ProfileInteractor;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.interactor.ProfileUseCase;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.interfaces.ProfilePresenter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.router.MainRouter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.interfaces.ProfileView;
import net.azurewebsites.streambeta.yandexstreamsandroid.util.RxUtils;
import net.azurewebsites.streambeta.yandexstreamsandroid.util.ThrowableToStringIdConverter;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;


public class ProfilePresenterImpl extends BasePresenter<ProfileView, MainRouter, Object>
        implements ProfilePresenter {

    private Disposable disposable = Disposables.empty();
    private ProfileInteractor profileInteractor;


    public ProfilePresenterImpl() {
        profileInteractor = new ProfileUseCase();
    }

    @Override
    public String getId() {
        return "profile";
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void saveInstanceState(Object instanceState) {

    }

    @Override
    public boolean isAuthorised() {
        if (!App.getAppInstance().getApiClient().isAuthorized()) {
            if (!App.getAppInstance().getPreferencesWrapper().getAuthToken("yandex_money").isEmpty()) {
                App.getAppInstance().getApiClient().setAccessToken(App.getAppInstance()
                        .getPreferencesWrapper().getAuthToken("yandex_money"));
            }
        }

        return App.getAppInstance().getApiClient().isAuthorized();
        //return !App.getAppInstance().getPreferencesWrapper().getAuthToken("yandex_money").isEmpty();
    }

    @Override
    public void getAccountInfo() {
        if (getView() != null) getView().showProgressbar();

        Single.fromCallable(() -> App.getAppInstance().getApiClient()
                .execute(new AccountInfo.Request()))
                .compose(RxUtils.applySingleSchedulers())
                .subscribe(response -> {
                    if (getView() != null) {
                        getView().hideProgressbar();
                        getView().setBalance(response.balance);
                        getView().saveAccountInfo(response);
                    }
                }, error -> {
                    if (getView() != null) {
                        getView().hideProgressbar();
                        error.printStackTrace();

                        if (error instanceof InvalidTokenException) {
                            App.getAppInstance().getApiClient().setAccessToken("");
                            App.getAppInstance().getPreferencesWrapper().setAuthToken("yandex_money", "");
                            getView().setUnauthorisedMode();
                        } else {
                            getView().showError(ThrowableToStringIdConverter.convert(error));
                        }
                    }
                });

    }

    @Override
    public void unauthorise() {
        if (getView() != null) getView().showProgressbar();

        profileInteractor.revokeYandexAccessKey(
                App.getAppInstance().getPreferencesWrapper().getAuthToken("yandex_money"))
                .compose(RxUtils.applyCompletableSchedulers())
                .doOnError(error -> {
                    error.printStackTrace();
                    if (getView() != null)
                        getView().showError(ThrowableToStringIdConverter.convert(error));
                })
                .subscribe(() -> {
                    if (getView() != null) {
                        getView().hideProgressbar();
                        getView().setUnauthorisedMode();

                        //delete token
                        App.getAppInstance().getApiClient().setAccessToken("");
                        App.getAppInstance().getPreferencesWrapper().setAuthToken("yandex_money", "");

                    }
                }, error -> {
                    if (getView() != null) getView().hideProgressbar();
                });
    }
}
