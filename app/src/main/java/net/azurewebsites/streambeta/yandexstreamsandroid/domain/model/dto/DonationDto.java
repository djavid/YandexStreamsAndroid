package net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DonationDto {

    @SerializedName("operation_id")
    @Expose
    private String operation_id;
    @SerializedName("stream_id")
    @Expose
    private int stream_id;
    @SerializedName("streamer_id")
    @Expose
    private String streamer_id;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("text_data")
    @Expose
    private String text_data;
    @SerializedName("amount")
    @Expose
    private int amount;
    @SerializedName("user")
    @Expose
    private String user_id;
    @SerializedName("sender")
    @Expose
    private String sender;


//    public DonationDto(String operation_id, int stream_id, String streamer_id, String date,
//                       String type, String text_data, int amount, long user_id, String sender) {
//        this.operation_id = operation_id;
//        this.stream_id = stream_id;
//        this.streamer_id = streamer_id;
//        this.date = date;
//        this.type = type;
//        this.text_data = text_data;
//        this.amount = amount;
//        this.user_id = user_id;
//        this.sender = sender;
//    }


    @Override
    public String toString() {
        return "DonationDto{" +
                "operation_id='" + operation_id + '\'' +
                ", stream_id=" + stream_id +
                ", streamer_id='" + streamer_id + '\'' +
                ", type='" + type + '\'' +
                ", text_data='" + text_data + '\'' +
                ", amount=" + amount +
                ", user_id=" + user_id +
                ", sender='" + sender + '\'' +
                '}';
    }

    public String getOperation_id() {
        return operation_id;
    }
    public void setOperation_id(String operation_id) {
        this.operation_id = operation_id;
    }

    public int getStream_id() {
        return stream_id;
    }
    public void setStream_id(int stream_id) {
        this.stream_id = stream_id;
    }

    public String getStreamer_id() {
        return streamer_id;
    }
    public void setStreamer_id(String streamer_id) {
        this.streamer_id = streamer_id;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getText_data() {
        return text_data;
    }
    public void setText_data(String text_data) {
        this.text_data = text_data;
    }

    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getSender() {
        return sender;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }

}
