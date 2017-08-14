
package net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.twitch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stream {

    @SerializedName("_id")
    @Expose
    private long id;
    @SerializedName("average_fps")
    @Expose
    private double averageFps;
    @SerializedName("channel")
    @Expose
    private Channel channel;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("delay")
    @Expose
    private long delay;
    @SerializedName("game")
    @Expose
    private String game;
    @SerializedName("is_playlist")
    @Expose
    private boolean isPlaylist;
    @SerializedName("preview")
    @Expose
    private Preview preview;
    @SerializedName("video_height")
    @Expose
    private long videoHeight;
    @SerializedName("viewers")
    @Expose
    private long viewers;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAverageFps() {
        return averageFps;
    }

    public void setAverageFps(double averageFps) {
        this.averageFps = averageFps;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public boolean isIsPlaylist() {
        return isPlaylist;
    }

    public void setIsPlaylist(boolean isPlaylist) {
        this.isPlaylist = isPlaylist;
    }

    public Preview getPreview() {
        return preview;
    }

    public void setPreview(Preview preview) {
        this.preview = preview;
    }

    public long getVideoHeight() {
        return videoHeight;
    }

    public void setVideoHeight(long videoHeight) {
        this.videoHeight = videoHeight;
    }

    public long getViewers() {
        return viewers;
    }

    public void setViewers(long viewers) {
        this.viewers = viewers;
    }

}
