package net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto;

/**
 * Created by Tetawex on 23.07.2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccessTokenResponse {

    @SerializedName("access_token")
    @Expose
    private String accessToken;

    public AccessTokenResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public AccessTokenResponse withAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

}
