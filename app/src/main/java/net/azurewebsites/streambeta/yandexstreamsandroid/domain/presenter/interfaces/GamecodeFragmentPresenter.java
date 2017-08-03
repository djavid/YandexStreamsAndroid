package net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.interfaces;

import net.azurewebsites.streambeta.yandexstreamsandroid.core.Presenter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.router.MainRouter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.interfaces.GamecodeFragmentView;

/**
 * Created by djavid on 03.08.17.
 */


public interface GamecodeFragmentPresenter extends Presenter<GamecodeFragmentView, MainRouter, Object> {
    void loadStreamByUrl(String url);
}
