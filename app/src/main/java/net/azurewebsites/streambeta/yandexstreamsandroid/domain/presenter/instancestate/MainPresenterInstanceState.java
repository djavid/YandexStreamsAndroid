package net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.instancestate;

import net.azurewebsites.streambeta.yandexstreamsandroid.domain.router.ScreenTag;

/**
 * Created by Tetawex on 15.07.2017.
 */

public class MainPresenterInstanceState {
    private ScreenTag selectedScreen;

    public ScreenTag getSelectedScreen() {
        return selectedScreen;
    }

    public void setSelectedScreen(ScreenTag selectedScreen) {
        this.selectedScreen = selectedScreen;
    }
}
