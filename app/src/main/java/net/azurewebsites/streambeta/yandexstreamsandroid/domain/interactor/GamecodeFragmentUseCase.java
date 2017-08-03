package net.azurewebsites.streambeta.yandexstreamsandroid.domain.interactor;

import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.DataRepository;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.RestDataRepository;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.StreamFeedItemDto;

import io.reactivex.Single;


/**
 * Created by djavid on 03.08.17.
 */


public class GamecodeFragmentUseCase implements GamecodeFragmentInteractor {

    private DataRepository dataRepository;

    public GamecodeFragmentUseCase(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public GamecodeFragmentUseCase() {
        this(new RestDataRepository());
    }

    @Override
    public Single<StreamFeedItemDto> getStreamByUrl(String url) {
        return dataRepository.getStreamByUrl(url);
    }
}
