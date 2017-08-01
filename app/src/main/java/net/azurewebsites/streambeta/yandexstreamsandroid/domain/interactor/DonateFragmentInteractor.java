package net.azurewebsites.streambeta.yandexstreamsandroid.domain.interactor;

import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.DonationDto;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.StreamSettingsDto;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by djavid on 30.07.17.
 */

public interface DonateFragmentInteractor {
    Single<StreamSettingsDto> getStreamSettings(int stream_id);
    Completable sendDonationWithId(DonationDto donation);
}
