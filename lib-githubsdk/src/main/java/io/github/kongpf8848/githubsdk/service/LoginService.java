

package io.github.kongpf8848.githubsdk.service;

import io.github.kongpf8848.githubsdk.model.OauthToken;
import io.reactivex.Observable;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginService {

    /**
     *获取Token
     */
    @POST("login/oauth/access_token")
    @Headers({"Domain:login","Accept: application/json"})
    Observable<OauthToken> getAccessToken(
            @Query("client_id") String clientId,
            @Query("client_secret") String clientSecret,
            @Query("code") String code
    );

}
