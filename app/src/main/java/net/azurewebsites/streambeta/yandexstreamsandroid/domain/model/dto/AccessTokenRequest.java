package net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto;

/**
 * Created by Tetawex on 23.07.2017.
 */


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccessTokenRequest {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("scope")
    @Expose
    private List<String> scope = null;
    @SerializedName("client_id")
    @Expose
    private String clientId;
    @SerializedName("grant_type")
    @Expose
    private String grantType;
    @SerializedName("redirect_uri")
    @Expose
    private String redirectUri;
    @SerializedName("type")
    @Expose
    private String type;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public AccessTokenRequest withCode(String code) {
        this.code = code;
        return this;
    }

    public List<String> getScope() {
        return scope;
    }

    public void setScope(List<String> scope) {
        this.scope = scope;
    }

    public AccessTokenRequest withScope(List<String> scope) {
        this.scope = scope;
        return this;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public AccessTokenRequest withClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public AccessTokenRequest withGrantType(String grantType) {
        this.grantType = grantType;
        return this;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public AccessTokenRequest withRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public AccessTokenRequest withType(String type) {
        this.type = type;
        return this;
    }

}
