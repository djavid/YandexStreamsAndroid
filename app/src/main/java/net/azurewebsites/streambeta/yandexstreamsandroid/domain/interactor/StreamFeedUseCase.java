package net.azurewebsites.streambeta.yandexstreamsandroid.domain.interactor;

import net.azurewebsites.streambeta.yandexstreamsandroid.domain.interactor.mapped.StreamFeedItemModel;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.DataRepository;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.RestDataRepository;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.StreamFeedItemDto;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.twitch.Stream;

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
                    List<StreamFeedItemModel> models = new ArrayList<StreamFeedItemModel>();
                    for (StreamFeedItemDto dto : list) {
                        StreamFeedItemModel model = new StreamFeedItemModel();
                        model.setId(dto.getStreamId());
                        model.setDescription(dto.getName());
                        model.setName(dto.getChannel());
                        model.setImageUrl(dto.getLogo());
                        model.setStreamer_id(dto.getStreamerId());
                        model.setAudience(dto.getViewers());
                        models.add(model);
                    }
                    return models;
                });
    }

    @Override
    public Single<List<StreamFeedItemModel>> getFollowedStreams(String stream_type, int limit,
                                                                int offset, String access_token) {

        return dataRepository.getFollowedStreams(stream_type, limit, offset, access_token)
                .map((list) -> {

                    List<StreamFeedItemModel> models = new ArrayList<>();
                    for (Stream dto : list.getStreams()) {
                        StreamFeedItemModel model = new StreamFeedItemModel();
                        model.setId(dto.getId());
                        model.setDescription(dto.getChannel().getStatus());
                        model.setName(dto.getChannel().getName());
                        model.setImageUrl(dto.getChannel().getLogo());
                        model.setStreamer_id(Long.toString(dto.getChannel().getId()));
                        model.setAudience(dto.getViewers());
                        models.add(model);
                    }
                    return models;
                });
    }
}
