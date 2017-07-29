package net.azurewebsites.streambeta.yandexstreamsandroid.core.view;

import net.azurewebsites.streambeta.yandexstreamsandroid.core.View;

import java.util.List;

/**
 * Created by Tetawex on 29.07.2017.
 */

public interface ScrollFeedView<T> extends View{
    void scrollToPosition(int position);
    void appendFeed(List<T> feed);
    void resetFeed();
}
