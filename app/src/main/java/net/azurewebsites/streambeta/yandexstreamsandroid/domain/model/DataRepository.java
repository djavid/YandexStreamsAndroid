package net.azurewebsites.streambeta.yandexstreamsandroid.domain.model;

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
 * Created by Tetawex on 15.07.2017.
 */

public interface DataRepository{
    Single<AccessTokenResponse> getUserCode(AccessTokenRequest request);
    Completable sendDonationWithId(DonationDto donation);
    Single<List<StreamFeedItemDto>> getStreamFeedForQuery(String query, int offset);
    Single<StreamSettingsDto> getStreamSettings(long stream_id);
    Single<StreamFeedItemDto> getStreamByUrl(String url);
    Single<TwitchFollowsDto> getFollowedStreams(String stream_type, int limit, int offset,
                                                String access_token);
    Completable revokeYandexAccessKey(String access_token);
}
