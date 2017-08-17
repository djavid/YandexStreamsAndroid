
package net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;


public class DonationHistoryDto {

    @SerializedName("user")
    @Expose
    private Object user;
    @SerializedName("operation_id")
    @Expose
    private String operationId;
    @SerializedName("stream_id")
    @Expose
    private Integer streamId;
    @SerializedName("streamer_id")
    @Expose
    private String streamerId;
    @SerializedName("date")
    @Expose
    private Date date;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("text_data")
    @Expose
    private String textData;
    @SerializedName("voice_data")
    @Expose
    private Object voiceData;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("user_id")
    @Expose
    private long userId;
    @SerializedName("answer")
    @Expose
    private String answer;
    @SerializedName("sender")
    @Expose
    private String sender;
    @SerializedName("stream_name")
    @Expose
    private String stream_name;
    @SerializedName("channel")
    @Expose
    private String channel;
    @SerializedName("stream_logo")
    @Expose
    private String stream_logo;
    @SerializedName("stream_preview")
    @Expose
    private String stream_preview;


    public Object getUser() {
        return user;
    }
    public void setUser(Object user) {
        this.user = user;
    }

    public String getOperationId() {
        return operationId;
    }
    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

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

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getTextData() {
        return textData;
    }
    public void setTextData(String textData) {
        this.textData = textData;
    }

    public Object getVoiceData() {
        return voiceData;
    }
    public void setVoiceData(Object voiceData) {
        this.voiceData = voiceData;
    }

    public float getAmount() {
        return amount / 100f;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getSender() {
        return sender;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getStream_name() {
        return stream_name;
    }
    public void setStream_name(String stream_name) {
        this.stream_name = stream_name;
    }

    public String getChannel() {
        return channel;
    }
    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getStream_logo() {
        return stream_logo;
    }
    public void setStream_logo(String stream_logo) {
        this.stream_logo = stream_logo;
    }

    public String getStream_preview() {
        return stream_preview;
    }
    public void setStream_preview(String stream_preview) {
        this.stream_preview = stream_preview;
    }
}
