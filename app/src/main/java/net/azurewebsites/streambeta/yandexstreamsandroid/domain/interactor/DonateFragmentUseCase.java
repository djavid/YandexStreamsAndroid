package net.azurewebsites.streambeta.yandexstreamsandroid.domain.interactor;

import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.DataRepository;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.RestDataRepository;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.DonationDto;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.StreamSettingsDto;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by djavid on 30.07.17.
 */

public class DonateFragmentUseCase implements DonateFragmentInteractor {

    private DataRepository dataRepository;

    public DonateFragmentUseCase(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public DonateFragmentUseCase() {
        this(new RestDataRepository());
    }

    @Override
    public Single<StreamSettingsDto> getStreamSettings(int stream_id) {
        return dataRepository.getStreamSettings(stream_id);
    }

    @Override
    public Completable sendDonationWithId(DonationDto donation) {
        return dataRepository.sendDonationWithId(donation)
                .doOnError(Throwable::printStackTrace);
    }
}
