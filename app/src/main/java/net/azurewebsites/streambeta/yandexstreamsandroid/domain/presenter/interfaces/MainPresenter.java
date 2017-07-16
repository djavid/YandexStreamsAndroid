package net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.interfaces;

import net.azurewebsites.streambeta.yandexstreamsandroid.core.Presenter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.instancestate.MainPresenterInstanceState;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.router.MainRouter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.router.ScreenTag;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.interfaces.MainView;

/**
 * Created by Tetawex on 15.07.2017.
 */

public interface MainPresenter extends Presenter<MainView, MainRouter, MainPresenterInstanceState>{
    void onScreenSwitched(ScreenTag screenTag);
}
