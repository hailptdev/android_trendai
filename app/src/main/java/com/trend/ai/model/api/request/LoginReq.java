package com.trend.ai.model.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Hailpt on 6/8/2018.
 */
public class LoginReq {

    @SerializedName("access_token")
    @Expose
    private String access_token;
    @SerializedName("access_token_secret")
    @Expose
    private String access_token_secret;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getAccess_token_secret() {
        return access_token_secret;
    }

    public void setAccess_token_secret(String access_token_secret) {
        this.access_token_secret = access_token_secret;
    }
}