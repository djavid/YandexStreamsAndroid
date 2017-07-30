package net.azurewebsites.streambeta.yandexstreamsandroid.domain.model;

import net.azurewebsites.streambeta.yandexstreamsandroid.domain.App;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.AccessTokenRequest;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.AccessTokenResponse;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.StreamFeedItemDto;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.StreamSettingsDto;

import java.util.List;

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
    public Single<List<StreamFeedItemDto>> getStreamFeedForQuery(String query, int offset) {
        return apiInterface.getStreamFeedForQuery(query, 20, offset);
    }

    @Override
    public Single<StreamSettingsDto> getStreamSettings(int stream_id) {
        return apiInterface.getStreamSettings(stream_id);
    }
}
