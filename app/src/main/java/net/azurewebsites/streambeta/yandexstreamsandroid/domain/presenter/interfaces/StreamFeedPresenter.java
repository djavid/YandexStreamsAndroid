package net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.interfaces;

import net.azurewebsites.streambeta.yandexstreamsandroid.core.BasePresenter;
import net.azurewebsites.streambeta.yandexstreamsandroid.core.Presenter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.instancestate.StreamFeedPresenterInstanceState;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.router.MainRouter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.interfaces.StreamFeedView;

/**
 * Created by Tetawex on 29.07.2017.
 */

public interface StreamFeedPresenter
        extends Presenter<StreamFeedView, MainRouter, StreamFeedPresenterInstanceState> {

    void onQueryStringModified(String query);
    void onQrButtonPressed();
    boolean isAuthorised();

    void loadTwitchFeed();
}
