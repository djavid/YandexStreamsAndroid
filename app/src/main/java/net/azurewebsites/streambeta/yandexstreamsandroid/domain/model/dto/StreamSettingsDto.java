package net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by djavid on 30.07.17.
 */


public class StreamSettingsDto {

    @SerializedName("stream_id")
    @Expose
    private int streamId;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("preview")
    @Expose
    private String preview;
    @SerializedName("text_l")
    @Expose
    private int textL;
    @SerializedName("voice_l")
    @Expose
    private int voiceL;
    @SerializedName("min_sum")
    @Expose
    private int minSum;


    public int getStreamId() {
        return streamId;
    }

    public void setStreamId(int streamId) {
        this.streamId = streamId;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public int getTextL() {
        return textL;
    }

    public void setTextL(int textL) {
        this.textL = textL;
    }

    public int getVoiceL() {
        return voiceL;
    }

    public void setVoiceL(int voiceL) {
        this.voiceL = voiceL;
    }

    public int getMinSum() {
        return minSum;
    }

    public void setMinSum(int minSum) {
        this.minSum = minSum;
    }

}