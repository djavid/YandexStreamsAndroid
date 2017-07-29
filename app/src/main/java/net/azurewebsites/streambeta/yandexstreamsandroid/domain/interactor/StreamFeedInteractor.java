package net.azurewebsites.streambeta.yandexstreamsandroid.domain.interactor;

import net.azurewebsites.streambeta.yandexstreamsandroid.domain.interactor.mapped.StreamFeedItemModel;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.DataRepository;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Tetawex on 28.07.2017.
 */

public interface StreamFeedInteractor {
    Single<List<StreamFeedItemModel>> getStreamFeedForQuery(String query, int offset);
}
