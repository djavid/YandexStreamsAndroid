package net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.interfaces;

import net.azurewebsites.streambeta.yandexstreamsandroid.core.Presenter;
import net.azurewebsites.streambeta.yandexstreamsandroid.core.view.DonateFragmentView;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.instancestate.DonateFragmentPresenterInstanceState;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.router.MainRouter;

/**
 * Created by djavid on 30.07.17.
 */


public interface DonateFragmentPresenter
        extends Presenter<DonateFragmentView, MainRouter, DonateFragmentPresenterInstanceState> {

    void loadStreamSettings(int stream_id);
}
