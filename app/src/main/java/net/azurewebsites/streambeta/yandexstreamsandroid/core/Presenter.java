package net.azurewebsites.streambeta.yandexstreamsandroid.core;

/**
 * Created by tetawex on 11.07.17.
 */

public interface Presenter<V extends View, R extends Router, InstanceState> {
    String getId();

    void onStart();

    void onStop();

    void saveInstanceState(InstanceState instanceState);

    V getView();

    R getRouter();

    void setView(V view);

    void setRouter(R router);
}
