package io.github.kongpf8848.githubsdk;

import io.github.kongpf8848.githubsdk.http.GitHubHttpCallback;
import io.github.kongpf8848.githubsdk.http.GitHubHttpObserver;
import io.github.kongpf8848.githubsdk.http.GitHubHttpInterceptor;
import io.github.kongpf8848.githubsdk.model.OauthToken;
import io.github.kongpf8848.githubsdk.model.User;
import io.github.kongpf8848.githubsdk.service.LoginService;
import io.github.kongpf8848.githubsdk.service.UserService;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GitHubSdk {

    private static GitHubSdk instance;
    private OkHttpClient client;
    private Retrofit retrofit;

    private GitHubSdk() {

    }

    public static GitHubSdk getInstance() {
        if (instance == null) {
            synchronized (GitHubSdk.class) {
                if (instance == null) {
                    instance = new GitHubSdk();
                }
            }
        }
        return instance;
    }

    public GitHubSdk client(OkHttpClient client) {
        this.client = client;
        return this;
    }

    public void init() {
        if (client == null) {
            client = new OkHttpClient();
        }
        OkHttpClient.Builder clientBuilder = client.newBuilder();
        clientBuilder.interceptors().add(0, new GitHubHttpInterceptor());

        retrofit = new Retrofit.Builder()
                .baseUrl("https://example.com/")
                .client(clientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private <T> void applyObservable(Observable<T> observable,GitHubHttpCallback<T>callback){
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new GitHubHttpObserver(callback));
    }

    /**
     * 获取token
     */
    public void getAccessToken(String clientId, String clientSecret, String code, GitHubHttpCallback<OauthToken> callback) {
        LoginService service = retrofit.create(LoginService.class);
        applyObservable(service.getAccessToken(clientId, clientSecret, code),callback);

    }

    /**
     * 获取用户信息
     */
    public void getUserInfo(GitHubHttpCallback<User> callback){
        UserService userService=retrofit.create(UserService.class);
        applyObservable(userService.getUserInfo(),callback);
    }


}
