package io.github.kongpf8848.githubsdk.http;

import androidx.annotation.NonNull;

import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;

public class GitHubHttpObserver<T> implements Observer<T>, Disposable {

    private final AtomicReference<Disposable> upstream = new AtomicReference<>();

    private final GitHubHttpCallback<T> callback;

    public GitHubHttpObserver(GitHubHttpCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        callback.onStart();
    }

    @Override
    public void onNext(@NonNull T t) {
        callback.onNext(t);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        callback.onError(e);
        dispose();
    }

    @Override
    public void onComplete() {
        callback.onComplete();
        dispose();
    }

    @Override
    public void dispose() {
        DisposableHelper.dispose(upstream);
    }

    @Override
    public boolean isDisposed() {
        return upstream.get() == DisposableHelper.DISPOSED;
    }
}
