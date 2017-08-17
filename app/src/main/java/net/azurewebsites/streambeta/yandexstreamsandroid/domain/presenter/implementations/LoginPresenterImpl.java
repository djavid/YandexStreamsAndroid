package net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.implementations;

import net.azurewebsites.streambeta.yandexstreamsandroid.core.BasePresenter;
import net.azurewebsites.streambeta.yandexstreamsandroid.core.Router;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.Config;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.interactor.AccessTokenInteractor;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.interactor.AccessTokenUseCase;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.instancestate.LoginPresenterInstanceState;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.interfaces.LoginPresenter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.interfaces.LoginView;
import net.azurewebsites.streambeta.yandexstreamsandroid.util.RxUtils;
import net.azurewebsites.streambeta.yandexstreamsandroid.util.ThrowableToStringIdConverter;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class LoginPresenterImpl
        extends BasePresenter<LoginView, Router, LoginPresenterInstanceState>
        implements LoginPresenter {

    private AccessTokenInteractor userCodeInteractor;

    private String authType;

    public LoginPresenterImpl() {
        super();
        userCodeInteractor = new AccessTokenUseCase();
    }

    @Override
    public String getId() {
        return "login";
    }

    @Override
    public void onStart() {
        System.out.println("onStart LoginPresenterImpl");
        if (getInstanceState() != null)
            getView().loadUrl(getInstanceState().getCurrentUrl());
        else {
            switch (authType) {
                case "twitch":
                    getView().loadUrl("https://api.twitch.tv/kraken/oauth2/authorize?"
                            + "client_id=" + Config.TWITCH_CLIENT_ID + "&"
                            + "redirect_uri=" + Config.WEB_APP_REDIRECT_URI + "&"
                            + "response_type=code&scope=user_read user_follows_edit");
                    break;
                case "yandex_money":
                    String url = "https://money.yandex.ru/oauth/authorize";
                    String postData = "";
                    try {
                        postData = "client_id=" + URLEncoder.encode(Config.YANDEX_MONEY_CLIENT_ID, "UTF-8")
                                + "&redirect_uri=" + URLEncoder.encode(Config.WEB_APP_REDIRECT_URI, "UTF-8")
                                + "&grant_type=" + URLEncoder.encode("authorisation", "UTF-8")
                                + "&scope=" + URLEncoder.encode("account-info payment-p2p money-source(\"wallet\",\"card\")", "UTF-8")
                                + "&response_type=" + URLEncoder.encode("code", "UTF-8");
                        getView().postUrl(url, postData.getBytes());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    @Override
    public void onStop() {
        setView(null);
    }

    @Override
    public void saveInstanceState(LoginPresenterInstanceState instanceState) {
        setInstanceState(instanceState);
    }

    @Override
    public void onInitialCodeReceived(String code) {
        if (getView() != null)
            getView().showProgressbar();
        String clientId = "";
        switch (authType) {
            case "twitch":
                clientId = Config.TWITCH_CLIENT_ID;
                break;
            case "yandex_money":
                clientId = Config.YANDEX_MONEY_CLIENT_ID;
                break;
        }
        userCodeInteractor.getAndSaveAccessCode(code, clientId, authType)
                .compose(RxUtils.applyCompletableSchedulers())
                .subscribe(() -> {
                            if (getView() != null) {
                                getView().setAuthResult(-1); //OK

                                getView().dispose();
                            }
                        },
                        throwable -> {
                            if (getView() != null) {
                                getView().setAuthResult(0); //CANCELED
                                getView().hideProgressbar();
                                getView().showError(
                                        ThrowableToStringIdConverter.convert(throwable));
                            }
                        });
    }

    @Override
    public String getAuthType() {
        return authType;
    }

    @Override
    public void setAuthType(String authType) {
        this.authType = authType;
    }
}
