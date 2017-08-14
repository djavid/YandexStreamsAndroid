package net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.interfaces;

import net.azurewebsites.streambeta.yandexstreamsandroid.core.view.ScrollFeedView;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.interactor.mapped.StreamFeedItemModel;


public interface StreamFeedView extends ScrollFeedView<StreamFeedItemModel> {
    void setQueryText(String query);
    void showLoginWarning();
    void hideLoginWarning();
}
