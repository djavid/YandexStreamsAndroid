package net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto;

/**
 * Created by djavid on 20.07.17.
 */


public class StreamModel {

    private long id;
    private String imageURL;
    private String name;
    private String description;
    private String audience;


    public StreamModel(long id, String imageURL, String name, String description, String audience) {
        this.id = id;
        this.imageURL = imageURL;
        this.name = name;
        this.description = description;
        this.audience = audience;
    }

    public StreamModel() { }


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
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
