package net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.instancestate;

import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.StreamSettingsDto;

/**
 * Created by djavid on 30.07.17.
 */

public class DonateFragmentPresenterInstanceState {

    private StreamSettingsDto streamSettingsDto;
    private String nick_name;
    private String donate_sum;
    private String donate_text;


    public DonateFragmentPresenterInstanceState(StreamSettingsDto streamSettingsDto) {
        this.streamSettingsDto = streamSettingsDto;
    }

    public DonateFragmentPresenterInstanceState(String nick_name, String donate_sum, String donate_text) {
        this.nick_name = nick_name;
        this.donate_sum = donate_sum;
        this.donate_text = donate_text;
    }

    public StreamSettingsDto getStreamSettingsDto() {
        return streamSettingsDto;
    }

    public void setStreamSettingsDto(StreamSettingsDto streamSettingsDto) {
        this.streamSettingsDto = streamSettingsDto;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getDonate_sum() {
        return donate_sum;
    }

    public void setDonate_sum(String donate_sum) {
        this.donate_sum = donate_sum;
    }

    public String getDonate_text() {
        return donate_text;
    }

    public void setDonate_text(String donate_text) {
        this.donate_text = donate_text;
    }
}
