package net.azurewebsites.streambeta.yandexstreamsandroid.domain;

import net.azurewebsites.streambeta.yandexstreamsandroid.core.Presenter;

import java.util.Map;

/**
 * Created by Tetawex on 15.07.2017.
 */

public class PresenterProvider {
    private Map<String, Presenter> presenterMap;

    public <T extends Presenter> T getPresenter(String presenterId, Class<T> c) {
        createPresenter(presenterId);
        return c.cast(presenterMap.get(presenterId));
    }
    private void createPresenter(String presenterId){
        if(presenterMap.containsKey(presenterId))
            return;
        switch (presenterId){
            //presenter id to class logic
        }
    }
    public void removePresenter(String presenterId){
        if(presenterMap.containsKey(presenterId))
            presenterMap.remove(presenterId);
    }
}