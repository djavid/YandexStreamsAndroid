package net.azurewebsites.streambeta.yandexstreamsandroid.domain.interactor.mapped;

/**
 * Created by djavid on 20.07.17.
 */


public class StreamFeedItemModel {

    private int id;
    private String imageUrl;
    private String name;
    private String description;
    private String audience;
    private String streamer_id;


    public StreamFeedItemModel(int id, String imageURL, String name, String description, String audience) {
        this.id = id;
        this.imageUrl = imageURL;
        this.name = name;
        this.description = description;
        this.audience = audience;
    }

    public StreamFeedItemModel() { }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public String getStreamer_id() {
        return streamer_id;
    }
    public void setStreamer_id(String streamer_id) {
        this.streamer_id = streamer_id;
    }
}
