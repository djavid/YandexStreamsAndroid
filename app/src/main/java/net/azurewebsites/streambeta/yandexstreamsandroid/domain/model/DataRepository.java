package net.azurewebsites.streambeta.yandexstreamsandroid.domain.model;

import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.AccessTokenRequest;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.AccessTokenResponse;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.StreamFeedItemDto;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.StreamSettingsDto;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Tetawex on 15.07.2017.
 */

public interface DataRepository{
    Single<AccessTokenResponse> getUserCode(AccessTokenRequest request);
    Single<List<StreamFeedItemDto>> getStreamFeedForQuery(String query, int offset);

    Single<StreamSettingsDto> getStreamSettings(int stream_id);
}
