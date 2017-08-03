package net.azurewebsites.streambeta.yandexstreamsandroid.domain.interactor;

import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.StreamFeedItemDto;

import io.reactivex.Single;

/**
 * Created by djavid on 03.08.17.
 */

public interface GamecodeFragmentInteractor {
    Single<StreamFeedItemDto> getStreamByUrl(String url);
}
