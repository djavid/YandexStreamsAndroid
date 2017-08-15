package net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.implementations;

import net.azurewebsites.streambeta.yandexstreamsandroid.core.BasePresenter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.instancestate.MainPresenterInstanceState;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.interfaces.MainPresenter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.router.MainRouter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.router.ScreenTag;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.interfaces.MainView;


public class MainPresenterImpl
        extends BasePresenter<MainView, MainRouter, MainPresenterInstanceState>
        implements MainPresenter {
    public MainPresenterImpl(MainView view, MainRouter router) {
        super(view, router);
    }

    @Override
    public String getId() {
        return "main";
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void saveInstanceState(MainPresenterInstanceState instanceState) {
        MainPresenterInstanceState state = new MainPresenterInstanceState();
        state.setSelectedScreen(getRouter().getCurrentScreen());
    }

    @Override
    public void onScreenSwitched(ScreenTag screenTag) {

    }
}
