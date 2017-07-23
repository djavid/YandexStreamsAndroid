package net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.instancestate;

/**
 * Created by Tetawex on 23.07.2017.
 */

public class LoginPresenterInstanceState {
    private String currentUrl;

    public LoginPresenterInstanceState(String currentUrl) {
        this.currentUrl = currentUrl;
    }

    public String getCurrentUrl() {
        return currentUrl;
    }

    public void setCurrentUrl(String currentUrl) {
        this.currentUrl = currentUrl;
    }
}
