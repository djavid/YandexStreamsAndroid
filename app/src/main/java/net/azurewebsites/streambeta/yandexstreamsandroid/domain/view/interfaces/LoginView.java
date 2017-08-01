package net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.interfaces;

import net.azurewebsites.streambeta.yandexstreamsandroid.core.View;

/**
 * Created by Tetawex on 23.07.2017.
 */

public interface LoginView extends View {
    void loadUrl(String url);
    void postUrl(String url, byte[] postData);
    boolean hasUrlLoaded();

    void setAuthResult(int result);
}
