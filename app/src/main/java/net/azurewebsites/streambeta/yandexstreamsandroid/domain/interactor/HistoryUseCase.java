package net.azurewebsites.streambeta.yandexstreamsandroid.domain.interactor;

import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.DataRepository;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.RestDataRepository;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.DonationHistoryDto;

import java.util.List;

import io.reactivex.Single;


public class HistoryUseCase implements HistoryInteractor {

    private DataRepository dataRepository;

    private HistoryUseCase(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public HistoryUseCase() {
        this(new RestDataRepository());
    }

    @Override
    public Single<List<DonationHistoryDto>> getDonationsHistory(String id) {
        return dataRepository.getDonationsHistory(id);
    }
}
