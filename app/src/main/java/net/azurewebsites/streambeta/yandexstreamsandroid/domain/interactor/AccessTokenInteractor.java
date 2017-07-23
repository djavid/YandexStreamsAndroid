package net.azurewebsites.streambeta.yandexstreamsandroid.domain.interactor;

import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Created by Tetawex on 23.07.2017.
 */

public interface AccessTokenInteractor {
    Completable getAndSaveAccessCode(String initialCode, String clientId, String authType);
}
