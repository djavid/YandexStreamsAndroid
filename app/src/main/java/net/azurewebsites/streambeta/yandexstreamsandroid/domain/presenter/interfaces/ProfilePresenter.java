package net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.interfaces;

import com.yandex.money.api.methods.wallet.AccountInfo;

import net.azurewebsites.streambeta.yandexstreamsandroid.core.Presenter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.router.MainRouter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.interfaces.ProfileView;

import io.reactivex.Single;


public interface ProfilePresenter extends Presenter<ProfileView, MainRouter, Object> {
    boolean isAuthorised();
    void getAccountInfo();
    void unauthorise();
}
