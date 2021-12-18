package io.github.kongpf8848.uuhub.mvp.presenter;

import io.github.kongpf8848.uuhub.db.dao.DaoSession;
import io.github.kongpf8848.uuhub.mvp.contract.IAddIssueContract;
import io.github.kongpf8848.uuhub.mvp.presenter.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by ThirtyDegreesRay on 2017/9/26 16:56:35
 */

public class IAddIssuePresenter extends BasePresenter<IAddIssueContract.View>
        implements IAddIssueContract.Presenter{

    @Inject
    public IAddIssuePresenter(DaoSession daoSession) {
        super(daoSession);
    }

}
