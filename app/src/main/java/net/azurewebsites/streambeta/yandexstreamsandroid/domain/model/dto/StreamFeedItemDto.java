package net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class StreamFeedItemDto {

    @SerializedName("stream_id")
    @Expose
    private Integer streamId;
    @SerializedName("streamer_id")
    @Expose
    private String streamerId;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("end_date")
    @Expose
    private Object endDate;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("channel")
    @Expose
    private String channel;
    @SerializedName("viewers")
    @Expose
    private Long viewers;
    @SerializedName("logo")
    @Expose
    private String logo;


    public Integer getStreamId() {
        return streamId;
    }
    public void setStreamId(Integer streamId) {
        this.streamId = streamId;
    }

    public String getStreamerId() {
        return streamerId;
    }
    public void setStreamerId(String streamerId) {
        this.streamerId = streamerId;
    }

    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Object getEndDate() {
        return endDate;
    }
    public void setEndDate(Object endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public String getChannel() {
        return channel;
    }
    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Long getViewers() {
        return viewers;
    }
    public void setViewers(Long viewers) {
        this.viewers = viewers;
    }

    public String getLogo() {
        return logo;
    }
    public void setLogo(String logo) {
        this.logo = logo;
    }
}
