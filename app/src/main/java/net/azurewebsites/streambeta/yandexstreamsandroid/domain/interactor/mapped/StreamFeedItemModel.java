package net.azurewebsites.streambeta.yandexstreamsandroid.domain.interactor.mapped;


public class StreamFeedItemModel {

    private long id;
    private String imageUrl;
    private String name;
    private String description;
    private Long audience;
    private String streamer_id;


    public StreamFeedItemModel(int id, String imageURL, String name, String description, Long audience) {
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

    public Long getAudience() {
        return audience;
    }
    public void setAudience(Long audience) {
        this.audience = audience;
    }

    public String getStreamer_id() {
        return streamer_id;
    }
    public void setStreamer_id(String streamer_id) {
        this.streamer_id = streamer_id;
    }
}
