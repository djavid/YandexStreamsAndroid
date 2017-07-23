package net.azurewebsites.streambeta.yandexstreamsandroid.domain.model;

import net.azurewebsites.streambeta.yandexstreamsandroid.domain.App;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.AccessTokenRequest;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.AccessTokenResponse;

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
}
