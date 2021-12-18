

package io.github.kongpf8848.uuhub.mvp.presenter;

import io.github.kongpf8848.uuhub.AppData;
import io.github.kongpf8848.uuhub.db.dao.DaoSession;
import io.github.kongpf8848.uuhub.mvp.contract.ISettingsContract;
import io.github.kongpf8848.uuhub.mvp.presenter.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created on 2017/8/1.
 *
 * @author ThirtyDegreesRay
 */

public class SettingsPresenter extends BasePresenter<ISettingsContract.View>
        implements ISettingsContract.Presenter{

    @Inject
    public SettingsPresenter(DaoSession daoSession) {
        super(daoSession);
    }

    @Override
    public void logout() {
        daoSession.getAuthUserDao().delete(AppData.INSTANCE.getAuthUser());
        AppData.INSTANCE.setAuthUser(null);
        AppData.INSTANCE.setLoggedUser(null);
        mView.showLoginPage();
    }

}
