

package io.github.kongpf8848.uuhub.http.core;

/**
 * Created by ThirtyDegreesRay on 2016/7/15 14:45
 */
public interface HttpObserver<T> {
    /**
     * Error
     * @param error
     */
    void onError(Throwable error);

    /**
     * success
     * @param response
     */
    void onSuccess(HttpResponse<T> response);
}
