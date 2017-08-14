package net.azurewebsites.streambeta.yandexstreamsandroid.domain.model;

import net.azurewebsites.streambeta.yandexstreamsandroid.domain.App;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.Config;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.AccessTokenRequest;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.AccessTokenResponse;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.DonationDto;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.StreamFeedItemDto;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.StreamSettingsDto;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.twitch.Stream;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.twitch.TwitchFollowsDto;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Tetawex on 23.07.2017.
 */

public class RestDataRepository implements DataRepository {
    private ApiInterface apiInterface;

    public RestDataRepository(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    public RestDataRepository() {
        this(App.getAppInstance().getApiInterface());
    }


    @Override
    public Single<AccessTokenResponse> getUserCode(AccessTokenRequest request) {
        return apiInterface.getUserCode(request);
    }

    @Override
    public Completable sendDonationWithId(DonationDto donation) {
        return apiInterface.sendDonationWithId(donation);
    }

    @Override
    public Single<List<StreamFeedItemDto>> getStreamFeedForQuery(String query, int offset) {
        return apiInterface.getStreamFeedForQuery(query, 20, offset);
    }

    @Override
    public Single<StreamSettingsDto> getStreamSettings(long stream_id) {
        return apiInterface.getStreamSettings(stream_id);
    }

    @Override
    public Single<StreamFeedItemDto> getStreamByUrl(String url) {
        return apiInterface.getStreamByUrl(url);
    }

    @Override
    public Single<TwitchFollowsDto> getFollowedStreams(String stream_type, int limit, int offset,
                                                   String access_token) {

        return apiInterface.getFollowedStreams(stream_type, limit, offset,
                "application/vnd.twitchtv.v5+json", Config.TWITCH_CLIENT_ID, "OAuth " + access_token);
    }

    @Override
    public Completable revokeYandexAccessKey(String access_token) {
        return apiInterface.revokeYandexAccessKey("Bearer " + access_token);
    }
}
