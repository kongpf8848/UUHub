package io.github.kongpf8848.githubsdk.http;

import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.kongpf8848.githubsdk.GitHubConfig;
import io.github.kongpf8848.githubsdk.GitHubUrls;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class GitHubHttpInterceptor implements Interceptor {

    private static final String TAG = "GitHubInterceptor";

    private static final String HEADER_DOMAIN="Domain";
    private static Map<String,String> urlMap=new HashMap<>();

    static {
        urlMap.put("login", GitHubUrls.GITHUB_BASE_URL);
        urlMap.put("user", GitHubUrls.GITHUB_API_BASE_URL);
    }

    private HttpUrl parseUrl(HttpUrl domainUrl, HttpUrl httpUrl) {
        if (domainUrl==null) {
            return httpUrl;
        }
        HttpUrl.Builder builder = httpUrl.newBuilder();
        for (int i = 0; i < httpUrl.pathSize(); i++) {
            builder.removePathSegment(0);
        }
        List<String> newPathSegments = new ArrayList<>();
        newPathSegments.addAll(domainUrl.encodedPathSegments());
        newPathSegments.addAll(httpUrl.encodedPathSegments());
        for (String path : newPathSegments) {
            builder.addEncodedPathSegment(path);
        }
        return builder
                .scheme(domainUrl.scheme())
                .host(domainUrl.host())
                .port(domainUrl.port())
                .build();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        Request.Builder requestBuilder = request.newBuilder();
        Response response=null;

        /**
         * 判断是否需要替换Url
         */
        HttpUrl httpUrl=request.url();
        HttpUrl newHttpUrl=null;
        String domain=request.header(HEADER_DOMAIN);
        if(!TextUtils.isEmpty(domain)){
            String newBaseUrl=urlMap.get(domain);
            if(!TextUtils.isEmpty(newBaseUrl)){
                HttpUrl domainUrl=HttpUrl.parse(newBaseUrl);
                if(domainUrl!=null) {
                    newHttpUrl = parseUrl(domainUrl,httpUrl);
                }
            }
        }
        if(newHttpUrl!=null){
            Log.d(TAG, "domainurl,old:"+httpUrl);
            Log.d(TAG, "domainurl,new:"+newHttpUrl);
            requestBuilder.url(newHttpUrl);
        }

        /**
         * 如果token不为空，添加token到Header
         */
        String token= GitHubConfig.token;
        if(!TextUtils.isEmpty(token)){
            Request newRequest=requestBuilder
                    .removeHeader(HEADER_DOMAIN)
                    .addHeader("Authorization","token "+token)
                    .build();
            response=chain.proceed(newRequest);
        }
        else{
            Request newRequest=requestBuilder
                    .removeHeader(HEADER_DOMAIN)
                    .build();
            response=chain.proceed(newRequest);
        }
        return response;
    }
}
