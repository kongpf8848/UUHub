package io.github.kongpf8848.githubsdk;

import androidx.annotation.NonNull;

import io.github.kongpf8848.githubsdk.model.User;
import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface UserService {

    @NonNull
    @GET("user")
    Observable<Response<User>> getUser();
}
