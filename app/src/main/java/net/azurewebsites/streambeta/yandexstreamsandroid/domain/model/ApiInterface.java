package net.azurewebsites.streambeta.yandexstreamsandroid.domain.model;

import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.AccessTokenRequest;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.AccessTokenResponse;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Tetawex on 15.07.2017.
 */

public interface ApiInterface {
    @POST("api/oauth")
    Single<AccessTokenResponse> getUserCode(@Body AccessTokenRequest request);
}
