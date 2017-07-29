package net.azurewebsites.streambeta.yandexstreamsandroid.domain.interactor.mapped;

/**
 * Created by djavid on 20.07.17.
 */


public class StreamFeedItemModel {

    private long id;
    private String imageUrl;
    private String name;
    private String description;
    private String audience;


    public StreamFeedItemModel(long id, String imageURL, String name, String description, String audience) {
        this.id = id;
        this.imageUrl = imageURL;
        this.name = name;
        this.description = description;
        this.audience = audience;
    }

    public StreamFeedItemModel() { }


    public long getId() {
        return id;
    }
    public void setId(long id) {
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
}
