package net.azurewebsites.streambeta.yandexstreamsandroid.domain.model;

import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.AccessTokenRequest;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.AccessTokenResponse;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.StreamFeedItemDto;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Tetawex on 15.07.2017.
 */

public interface ApiInterface {
    @POST("api/oauth")
    Single<AccessTokenResponse> getUserCode(@Body AccessTokenRequest request);

    @GET("/api/streams/Search?req=j&limit=1&offset=1")
    Single<List<StreamFeedItemDto>> getStreamFeedForQuery(
            @Query("req") String query, @Query("limit") int size, @Query("offset") int offset);
}
