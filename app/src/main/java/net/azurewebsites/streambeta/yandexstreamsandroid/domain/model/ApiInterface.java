package net.azurewebsites.streambeta.yandexstreamsandroid.domain.model;

import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.AccessTokenRequest;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.AccessTokenResponse;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.DonationDto;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.DonationHistoryDto;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.StreamFeedItemDto;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.StreamSettingsDto;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.twitch.TwitchFollowsDto;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ApiInterface {
    @POST("api/oauth")
    Single<AccessTokenResponse> getUserCode(@Body AccessTokenRequest request);

    @POST("api/donations")
    Completable sendDonationWithId(@Body DonationDto donation);

    @GET("api/donations/GetAllDonations")
    Single<List<DonationHistoryDto>> getDonationsHistory(
            @Query("id") String id, @Query("type") String type);

    @FormUrlEncoded
    @POST("api/users")
    Completable createUser(@Field("device_id") String deviсe_id);

    @GET("/api/streams/Search")
    Single<List<StreamFeedItemDto>> getStreamFeedForQuery(
            @Query("req") String query, @Query("limit") int size, @Query("offset") int offset);

    @GET("/api/Streams/GetOnlineSettings")
    Single<StreamSettingsDto> getStreamSettings(@Query("stream_id") long stream_id);

    @GET("/api/Streams")
    Single<StreamFeedItemDto> getStreamByUrl(@Query("url") String url);

    @GET("https://api.twitch.tv/kraken/streams/followed")
    Single<TwitchFollowsDto> getFollowedStreams(
            @Query("stream_type") String stream_type,
            @Query("limit") int limit,
            @Query("offset") int offset,
            @Header("Accept") String accept,
            @Header("Client-ID") String client_id,
            @Header("Authorization") String access_token
    );

    @POST("https://money.yandex.ru/api/revoke")
    Completable revokeYandexAccessKey(@Header("Authorization") String access_token);

}
