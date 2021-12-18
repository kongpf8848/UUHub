

package io.github.kongpf8848.githubsdk.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


/**
 * {"access_token":"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","token_type":"bearer","scope":"gist,notifications,repo,user"}
 */
public class OauthToken implements Serializable {

    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("token_type")
    private String tokenType;

    private String scope;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }


    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }


    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
