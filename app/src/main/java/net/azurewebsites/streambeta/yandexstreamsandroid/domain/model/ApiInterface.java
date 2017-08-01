package net.azurewebsites.streambeta.yandexstreamsandroid.domain.model;

import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.AccessTokenRequest;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.AccessTokenResponse;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.DonationDto;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.StreamFeedItemDto;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.StreamSettingsDto;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Tetawex on 15.07.2017.
 */


public interface ApiInterface {
    @POST("api/oauth")
    Single<AccessTokenResponse> getUserCode(@Body AccessTokenRequest request);

    @POST("api/donations")
    Completable sendDonationWithId(@Body DonationDto donation);

    @FormUrlEncoded
    @POST("api/users")
    Completable createUser(@Field("device_id") String deviсe_id);

    @GET("/api/streams/Search")
    Single<List<StreamFeedItemDto>> getStreamFeedForQuery(
            @Query("req") String query, @Query("limit") int size, @Query("offset") int offset);

    @GET("https://www.yastream.win/api/Streams/GetOnlineSettings")
    Single<StreamSettingsDto> getStreamSettings(@Query("stream_id") int stream_id);
}
