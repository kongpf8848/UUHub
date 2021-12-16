package io.github.kongpf8848.githubsdk.http;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class GitHubHttpCallback<T>{

    private Type type;

    public GitHubHttpCallback(){
        Type superclass = getClass().getGenericSuperclass();
        if (superclass instanceof Class) {
            type=getClass();
        } else {
            ParameterizedType parameterized =(ParameterizedType)superclass;
            type=parameterized.getActualTypeArguments()[0];
        }
    }

    public void onStart(){ }
    abstract public void onNext(T response);
    abstract public void onError(Throwable e);
    public void onComplete(){}
}
