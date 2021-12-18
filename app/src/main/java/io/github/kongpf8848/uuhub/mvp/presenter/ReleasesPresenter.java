package io.github.kongpf8848.uuhub.mvp.presenter;

import com.thirtydegreesray.dataautoaccess.annotation.AutoAccess;
import io.github.kongpf8848.uuhub.db.dao.DaoSession;
import io.github.kongpf8848.uuhub.http.core.HttpObserver;
import io.github.kongpf8848.uuhub.http.core.HttpResponse;
import io.github.kongpf8848.uuhub.mvp.contract.IReleasesContract;
import io.github.kongpf8848.uuhub.mvp.model.Release;
import io.github.kongpf8848.uuhub.mvp.presenter.base.BasePresenter;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Response;
import io.reactivex.Observable;

/**
 * Created by ThirtyDegreesRay on 2017/9/16 11:31:07
 */

public class ReleasesPresenter extends BasePresenter<IReleasesContract.View>
        implements IReleasesContract.Presenter{

    @AutoAccess String owner;
    @AutoAccess String repo;
    private ArrayList<Release> releases;

    @Inject
    public ReleasesPresenter(DaoSession daoSession) {
        super(daoSession);
    }

    @Override
    public void onViewInitialized() {
        super.onViewInitialized();
        if(releases == null){
            loadReleases(1, false);
        } else {
            mView.showReleases(releases);
            mView.hideLoading();
        }
    }

    @Override
    public void loadReleases(final int page, final boolean isReload) {
        mView.showLoading();
        final boolean readCacheFirst = page == 1 && !isReload;
        final HttpObserver<ArrayList<Release>> httpObserver = new HttpObserver<ArrayList<Release>>() {
            @Override
            public void onError(Throwable error) {
                mView.hideLoading();
            }

            @Override
            public void onSuccess(HttpResponse<ArrayList<Release>> response) {
                mView.hideLoading();
                if (isReload || releases == null || readCacheFirst) {
                    releases = response.body();
                } else {
                    releases.addAll(response.body());
                }
                if(response.body().size() == 0 && releases.size() != 0){
                    mView.setCanLoadMore(false);
                } else {
                    mView.showReleases(releases);
                }
            }
        };

        generalRxHttpExecute(new IObservableCreator<ArrayList<Release>>() {
            @Override
            public Observable<Response<ArrayList<Release>>> createObservable(boolean forceNetWork) {
                return getRepoService().getReleases(forceNetWork, owner, repo, page);
            }
        }, httpObserver, readCacheFirst);

    }

    public String getRepoName(){
        return repo;
    }

    public String getOwner() {
        return owner;
    }
}
