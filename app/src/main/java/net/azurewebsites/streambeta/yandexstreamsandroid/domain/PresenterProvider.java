package net.azurewebsites.streambeta.yandexstreamsandroid.domain;

import net.azurewebsites.streambeta.yandexstreamsandroid.core.Presenter;
import net.azurewebsites.streambeta.yandexstreamsandroid.core.Router;
import net.azurewebsites.streambeta.yandexstreamsandroid.core.View;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.implementations.LoginPresenterImpl;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.instancestate.LoginPresenterInstanceState;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.interfaces.LoginView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tetawex on 15.07.2017.
 */

public class PresenterProvider {
    private Map<String, Presenter> presenterMap;

    public PresenterProvider() {
        presenterMap = new HashMap<>();
    }

    public <T extends Presenter> T getPresenter(String presenterId, Class<T> c) {
        createPresenter(presenterId);
        return c.cast(presenterMap.get(presenterId));
    }

    private void createPresenter(String presenterId) {
        if (presenterMap.containsKey(presenterId))
            return;
        switch (presenterId) {
            case "login":
                presenterMap.put(presenterId, new LoginPresenterImpl());
        }
    }

    public void removePresenter(String presenterId) {
        if (presenterMap.containsKey(presenterId))
            presenterMap.remove(presenterId);
    }
}