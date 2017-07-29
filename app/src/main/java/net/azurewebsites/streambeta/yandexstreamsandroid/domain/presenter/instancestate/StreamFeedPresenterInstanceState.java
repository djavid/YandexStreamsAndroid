package net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.instancestate;

import net.azurewebsites.streambeta.yandexstreamsandroid.domain.interactor.mapped.StreamFeedItemModel;

import java.util.List;

/**
 * Created by Tetawex on 29.07.2017.
 */

public class StreamFeedPresenterInstanceState {
    private String query;
    private int scrollPosition;
    private List<StreamFeedItemModel> data;

    public StreamFeedPresenterInstanceState(String query, int scrollPosition, List<StreamFeedItemModel> data) {
        this.query = query;
        this.scrollPosition = scrollPosition;
        this.data = data;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getScrollPosition() {
        return scrollPosition;
    }

    public void setScrollPosition(int scrollPosition) {
        this.scrollPosition = scrollPosition;
    }

    public List<StreamFeedItemModel> getData() {
        return data;
    }

    public void setData(List<StreamFeedItemModel> data) {
        this.data = data;
    }
}
