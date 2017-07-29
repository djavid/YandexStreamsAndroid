package net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto;

/**
 * Created by djavid on 20.07.17.
 */


public class StreamModel {

    private long stream_id;
    private String streamer_id;
    private String imageURL;
    private String name;
    private String description;
    private String audience;


    public StreamModel(long stream_id, String streamer_id, String imageURL, String name, String description, String audience) {
        this.stream_id = stream_id;
        this.streamer_id = streamer_id;
        this.imageURL = imageURL;
        this.name = name;
        this.description = description;
        this.audience = audience;
    }

    public StreamModel() { }


    public long getStream_id() {
        return stream_id;
    }
    public void setStream_id(long stream_id) {
        this.stream_id = stream_id;
    }

    public String getStreamer_id() {
        return streamer_id;
    }
    public void setStreamer_id(String streamer_id) {
        this.streamer_id = streamer_id;
    }

    public String getImageURL() {
        return imageURL;
    }
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getAudience() {
        return audience;
    }
    public void setAudience(String audience) {
        this.audience = audience;
    }
}
