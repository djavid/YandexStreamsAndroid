package net.azurewebsites.streambeta.yandexstreamsandroid.domain.interactor;

import android.util.Log;

import net.azurewebsites.streambeta.yandexstreamsandroid.domain.App;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.Config;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.DataRepository;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.RestDataRepository;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.AccessTokenRequest;
import net.azurewebsites.streambeta.yandexstreamsandroid.util.RxUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;

/**
 * Created by Tetawex on 23.07.2017.
 */

public class AccessTokenUseCase implements AccessTokenInteractor {
    private DataRepository repository;

    public AccessTokenUseCase() {
        repository = new RestDataRepository();
    }

    @Override
    public Completable getAndSaveAccessCode(String initialCode, String clientId, String authType) {
        List<String> scope = new ArrayList<>();
        scope.add("user_follows_edit");
        scope.add("user_read");
        return repository.getUserCode(
                new AccessTokenRequest()
                        .withCode(initialCode)
                        .withClientId(clientId)
                        .withGrantType("authorization_code")
                        .withRedirectUri(Config.WEB_APP_REDIRECT_URI)
                        .withType(authType)
                        .withScope(scope))
                .doOnSuccess(accessTokenResponse -> {
                    App.getAppInstance()
                            .getPreferencesWrapper()
                            .setAuthToken(authType,accessTokenResponse.getAccessToken());
                })
                .toCompletable();
    }
}
