package net.azurewebsites.streambeta.yandexstreamsandroid.domain.interactor;

import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.DataRepository;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.RestDataRepository;

import io.reactivex.Completable;


public class ProfileUseCase implements ProfileInteractor {

    private DataRepository dataRepository;

    private ProfileUseCase(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public ProfileUseCase() {
        this(new RestDataRepository());
    }

    @Override
    public Completable revokeYandexAccessKey(String access_token) {
        return dataRepository.revokeYandexAccessKey(access_token);
    }
}
