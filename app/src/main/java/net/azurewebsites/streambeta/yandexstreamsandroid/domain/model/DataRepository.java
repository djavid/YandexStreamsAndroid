package net.azurewebsites.streambeta.yandexstreamsandroid.domain.model;

import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.AccessTokenRequest;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.AccessTokenResponse;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Tetawex on 15.07.2017.
 */

public interface DataRepository{
    Single<AccessTokenResponse> getUserCode(AccessTokenRequest request);
}
