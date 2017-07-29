package net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto;

import android.support.annotation.Nullable;

import java.math.BigInteger;
import java.sql.Types;
import java.util.Date;

/**
 * Created by djavid on 25.07.17.
 */


public class DonationModel {

    private String operation_id;
    private String streamer_id;
    private int stream_id;
    private int amount;
    @Nullable
    private BigInteger user_id;
    @Nullable
    private String sender;
    @Nullable
    private Date datetime;
    @Nullable
    private String status;
    @Nullable
    private String type;
    @Nullable
    private String text_data;
    @Nullable
    private String answer;
    @Nullable
    private String voice_data;


    public DonationModel(String operation_id, String streamer_id, int stream_id, int amount,
                         @Nullable BigInteger user_id, @Nullable String sender, @Nullable Date datetime,
                         @Nullable String status, @Nullable String type, @Nullable String text_data,
                         @Nullable String answer, @Nullable String voice_data) {
        this.operation_id = operation_id;
        this.streamer_id = streamer_id;
        this.stream_id = stream_id;
        this.amount = amount;
        this.user_id = user_id;
        this.sender = sender;
        this.datetime = datetime;
        this.status = status;
        this.type = type;
        this.text_data = text_data;
        this.answer = answer;
        this.voice_data = voice_data;
    }

    public String getOperation_id() {
        return operation_id;
    }
    public void setOperation_id(String operation_id) {
        this.operation_id = operation_id;
    }

    public String getStreamer_id() {
        return streamer_id;
    }
    public void setStreamer_id(String streamer_id) {
        this.streamer_id = streamer_id;
    }

    public int getStream_id() {
        return stream_id;
    }
    public void setStream_id(int stream_id) {
        this.stream_id = stream_id;
    }

    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Nullable
    public BigInteger getUser_id() {
        return user_id;
    }
    public void setUser_id(@Nullable BigInteger user_id) {
        this.user_id = user_id;
    }

    @Nullable
    public String getSender() {
        return sender;
    }
    public void setSender(@Nullable String sender) {
        this.sender = sender;
    }

    @Nullable
    public Date getDatetime() {
        return datetime;
    }
    public void setDatetime(@Nullable Date datetime) {
        this.datetime = datetime;
    }

    @Nullable
    public String getStatus() {
        return status;
    }
    public void setStatus(@Nullable String status) {
        this.status = status;
    }

    @Nullable
    public String getType() {
        return type;
    }
    public void setType(@Nullable String type) {
        this.type = type;
    }

    @Nullable
    public String getText_data() {
        return text_data;
    }
    public void setText_data(@Nullable String text_data) {
        this.text_data = text_data;
    }

    @Nullable
    public String getAnswer() {
        return answer;
    }
    public void setAnswer(@Nullable String answer) {
        this.answer = answer;
    }

    @Nullable
    public String getVoice_data() {
        return voice_data;
    }
    public void setVoice_data(@Nullable String voice_data) {
        this.voice_data = voice_data;
    }
}
