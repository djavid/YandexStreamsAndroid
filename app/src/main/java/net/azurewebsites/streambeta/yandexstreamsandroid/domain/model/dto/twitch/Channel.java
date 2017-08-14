
package net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.twitch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Channel {

    @SerializedName("_id")
    @Expose
    private long id;
    @SerializedName("broadcaster_language")
    @Expose
    private String broadcasterLanguage;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("display_name")
    @Expose
    private String displayName;
    @SerializedName("followers")
    @Expose
    private long followers;
    @SerializedName("game")
    @Expose
    private String game;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("mature")
    @Expose
    private boolean mature;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("partner")
    @Expose
    private boolean partner;
    @SerializedName("profile_banner")
    @Expose
    private String profileBanner;
    @SerializedName("profile_banner_background_color")
    @Expose
    private Object profileBannerBackgroundColor;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("video_banner")
    @Expose
    private String videoBanner;
    @SerializedName("views")
    @Expose
    private long views;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBroadcasterLanguage() {
        return broadcasterLanguage;
    }

    public void setBroadcasterLanguage(String broadcasterLanguage) {
        this.broadcasterLanguage = broadcasterLanguage;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public long getFollowers() {
        return followers;
    }

    public void setFollowers(long followers) {
        this.followers = followers;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public boolean isMature() {
        return mature;
    }

    public void setMature(boolean mature) {
        this.mature = mature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPartner() {
        return partner;
    }

    public void setPartner(boolean partner) {
        this.partner = partner;
    }

    public String getProfileBanner() {
        return profileBanner;
    }

    public void setProfileBanner(String profileBanner) {
        this.profileBanner = profileBanner;
    }

    public Object getProfileBannerBackgroundColor() {
        return profileBannerBackgroundColor;
    }

    public void setProfileBannerBackgroundColor(Object profileBannerBackgroundColor) {
        this.profileBannerBackgroundColor = profileBannerBackgroundColor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVideoBanner() {
        return videoBanner;
    }

    public void setVideoBanner(String videoBanner) {
        this.videoBanner = videoBanner;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }

}
