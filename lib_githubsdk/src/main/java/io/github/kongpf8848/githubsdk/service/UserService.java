package io.github.kongpf8848.githubsdk.service;

import androidx.annotation.NonNull;

import io.github.kongpf8848.githubsdk.model.User;
import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface UserService {

    /**
     * 获取用户信息
     */
    @NonNull
    @GET("user")
    @Headers({"Domain:user","Accept: application/json"})
    Observable<User> getUserInfo();
}
