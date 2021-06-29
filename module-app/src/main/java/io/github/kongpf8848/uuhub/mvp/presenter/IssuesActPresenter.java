package io.github.kongpf8848.uuhub.mvp.presenter;

import io.github.kongpf8848.uuhub.dao.DaoSession;
import io.github.kongpf8848.uuhub.mvp.contract.IIssuesActContract;
import io.github.kongpf8848.uuhub.mvp.presenter.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by ThirtyDegreesRay on 2017/9/20 17:22:16
 */

public class IssuesActPresenter extends BasePresenter<IIssuesActContract.View>
        implements IIssuesActContract.Presenter{

    @Inject
    public IssuesActPresenter(DaoSession daoSession) {
        super(daoSession);
    }

}
