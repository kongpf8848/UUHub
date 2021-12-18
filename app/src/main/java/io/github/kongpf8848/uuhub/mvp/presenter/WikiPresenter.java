package io.github.kongpf8848.uuhub.mvp.presenter;

import com.thirtydegreesray.dataautoaccess.annotation.AutoAccess;
import io.github.kongpf8848.uuhub.db.dao.DaoSession;
import io.github.kongpf8848.uuhub.http.core.HttpObserver;
import io.github.kongpf8848.uuhub.http.core.HttpResponse;
import io.github.kongpf8848.uuhub.mvp.contract.IWikiContract;
import io.github.kongpf8848.uuhub.mvp.model.WikiFeedModel;
import io.github.kongpf8848.uuhub.mvp.model.WikiModel;
import io.github.kongpf8848.uuhub.mvp.presenter.base.BasePresenter;

import org.xmlpull.v1.XmlPullParserException;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by ThirtyDegreesRay on 2017/12/6 16:41:32
 */

public class WikiPresenter extends BasePresenter<IWikiContract.View>
        implements IWikiContract.Presenter {

    @AutoAccess String owner;
    @AutoAccess String repo;
    private ArrayList<WikiModel> wikiList;

    @Inject
    public WikiPresenter(DaoSession daoSession) {
        super(daoSession);
    }

    @Override
    public void onViewInitialized() {
        super.onViewInitialized();
        loadWiki(false);
    }

    @Override
    public void loadWiki(boolean isReload) {
        mView.showLoading();
        HttpObserver<WikiFeedModel> httpObserver = new HttpObserver<WikiFeedModel>() {
            @Override
            public void onError(Throwable error) {
                mView.hideLoading();
                if (error.getCause() != null && error.getCause() instanceof XmlPullParserException) {
                    mView.showWiki(null);
                } else {
                    mView.showLoadError(getErrorTip(error));
                }
            }

            @Override
            public void onSuccess(HttpResponse<WikiFeedModel> response) {
                mView.hideLoading();
                wikiList = response.body().getWikiList();
                mView.showWiki(wikiList);
            }
        };
        generalRxHttpExecute(forceNetWork -> getGitHubWebPageService()
                        .getWiki(forceNetWork, owner, repo)
                , httpObserver, !isReload);

    }

}
