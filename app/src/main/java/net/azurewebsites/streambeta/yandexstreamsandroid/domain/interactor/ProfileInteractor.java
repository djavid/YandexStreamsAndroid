package net.azurewebsites.streambeta.yandexstreamsandroid.domain.interactor;

import io.reactivex.Completable;


public interface ProfileInteractor {
    Completable revokeYandexAccessKey(String access_token);
}
