package net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.interfaces;

import net.azurewebsites.streambeta.yandexstreamsandroid.core.View;

/**
 * Created by djavid on 03.08.17.
 */


public interface GamecodeFragmentView extends View {
    boolean isCameraPermissionGranted();
    void openDonateFragment(int streamId, String streamName, String streamer_id);
}
