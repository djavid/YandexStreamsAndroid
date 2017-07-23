package net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.interfaces;

import net.azurewebsites.streambeta.yandexstreamsandroid.core.Presenter;
import net.azurewebsites.streambeta.yandexstreamsandroid.core.Router;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.instancestate.LoginPresenterInstanceState;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.interfaces.LoginView;

/**
 * Created by Tetawex on 23.07.2017.
 */

public interface LoginPresenter extends Presenter<LoginView, Router, LoginPresenterInstanceState> {
    void onInitialCodeReceived(String code);
    String getAuthType();
    void setAuthType(String authType);
}
