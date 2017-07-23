package net.azurewebsites.streambeta.yandexstreamsandroid.core;

/**
 * Created by tetawex on 11.07.17.
 */

public interface View {
    void showProgressbar();
    void hideProgressbar();
    void showError(int errorId);
    void dispose();
}
