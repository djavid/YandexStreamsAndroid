package net.azurewebsites.streambeta.yandexstreamsandroid.domain.router;

import net.azurewebsites.streambeta.yandexstreamsandroid.core.Router;

/**
 * Created by Tetawex on 15.07.2017.
 */

public interface MainRouter extends Router {
    void goToScreen(ScreenTag screenTag);
    void goToStreamPage(int streamId, String streamName);
    ScreenTag getCurrentScreen();
}
