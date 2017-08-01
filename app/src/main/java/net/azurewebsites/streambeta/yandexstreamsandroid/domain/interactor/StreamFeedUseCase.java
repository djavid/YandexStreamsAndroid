package net.azurewebsites.streambeta.yandexstreamsandroid.domain.interactor;

import net.azurewebsites.streambeta.yandexstreamsandroid.domain.interactor.mapped.StreamFeedItemModel;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.DataRepository;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.RestDataRepository;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.StreamFeedItemDto;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

/**
 * Created by Tetawex on 28.07.2017.
 */

public class StreamFeedUseCase implements StreamFeedInteractor {
    public StreamFeedUseCase(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public StreamFeedUseCase() {
        this(new RestDataRepository());
    }

    private DataRepository dataRepository;

    @Override
    public Single<List<StreamFeedItemModel>> getStreamFeedForQuery(String query, int offset) {
        return dataRepository.getStreamFeedForQuery(query, offset)
                .map((list) -> {
                    //I can use flatmap and then map items one by one, but I don't care...
                    List<StreamFeedItemModel> models = new ArrayList<StreamFeedItemModel>();
                    for (StreamFeedItemDto dto : list) {
                        StreamFeedItemModel model = new StreamFeedItemModel();
                        model.setId(dto.getStreamId());
                        model.setDescription(dto.getName());
                        model.setName(dto.getChannel());
                        model.setImageUrl("http://streambeta.azurewebsites.net/api/images?id="
                                + dto.getStreamId() + "&type=logo");
                        model.setStreamer_id(dto.getStreamerId());
                        models.add(model);
                    }
                    return models;
                });
    }
}
