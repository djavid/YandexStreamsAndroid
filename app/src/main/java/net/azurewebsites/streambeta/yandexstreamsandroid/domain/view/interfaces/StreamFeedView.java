package net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.interfaces;

import net.azurewebsites.streambeta.yandexstreamsandroid.core.View;
import net.azurewebsites.streambeta.yandexstreamsandroid.core.view.ScrollFeedView;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.interactor.mapped.StreamFeedItemModel;

import java.util.List;

/**
 * Created by Tetawex on 28.07.2017.
 */

public interface StreamFeedView extends ScrollFeedView<StreamFeedItemModel> {
    void setQueryText(String query);
}
