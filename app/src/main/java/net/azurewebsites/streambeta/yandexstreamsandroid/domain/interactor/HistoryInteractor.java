package net.azurewebsites.streambeta.yandexstreamsandroid.domain.interactor;

import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.DonationHistoryDto;

import java.util.List;

import io.reactivex.Single;


public interface HistoryInteractor {
    Single<List<DonationHistoryDto>> getDonationsHistory(String id);
}
