package net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.interfaces;

import android.app.AlertDialog;

import net.azurewebsites.streambeta.yandexstreamsandroid.core.View;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.StreamSettingsDto;

/**
 * Created by djavid on 30.07.17.
 */

public interface DonateFragmentView extends View {
    void setStreamSettings(StreamSettingsDto streamSettings);

    void setNicknameText(String nicknameText);
    void setDonateText(String donateText);
    void setSumText(String donateSumText);

    String getNicknameText();
    String getDonateText();
    int getSumText();

    AlertDialog.Builder createDonateAlertDialog();

    String getStreamerId();
}
