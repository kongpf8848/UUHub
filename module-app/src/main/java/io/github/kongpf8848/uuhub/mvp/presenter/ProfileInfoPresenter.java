

package io.github.kongpf8848.uuhub.mvp.presenter;

import com.thirtydegreesray.dataautoaccess.annotation.AutoAccess;
import io.github.kongpf8848.uuhub.dao.DaoSession;
import io.github.kongpf8848.uuhub.http.core.HttpObserver;
import io.github.kongpf8848.uuhub.http.core.HttpResponse;
import io.github.kongpf8848.uuhub.mvp.contract.IProfileInfoContract;
import io.github.kongpf8848.uuhub.mvp.model.User;
import io.github.kongpf8848.uuhub.mvp.presenter.base.BasePagerPresenter;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Response;
import io.reactivex.Observable;

/**
 * Created by ThirtyDegreesRay on 2017/8/23 14:37:51
 */

public class ProfileInfoPresenter extends BasePagerPresenter<IProfileInfoContract.View>
        implements IProfileInfoContract.Presenter{

    @AutoAccess User user;
    private ArrayList<User> orgs;

    @Inject
    public ProfileInfoPresenter(DaoSession daoSession) {
        super(daoSession);
    }

    @Override
    public void onViewInitialized() {
        super.onViewInitialized();
    }

    @Override
    protected void loadData() {
        mView.showProfileInfo(user);
        if(user.isUser()) loadOrgs();
    }

    public User getUser() {
        return user;
    }

    private void loadOrgs(){
        if(orgs != null && orgs.size() != 0){
            mView.showUserOrgs(orgs);
            return;
        }
        HttpObserver<ArrayList<User>> httpObserver = new HttpObserver<ArrayList<User>>() {
            @Override
            public void onError(Throwable error) {
                mView.showErrorToast(getErrorTip(error));
            }

            @Override
            public void onSuccess(HttpResponse<ArrayList<User>> response) {
                if(response.body().size() != 0){
                    orgs = response.body();
                    mView.showUserOrgs(orgs);
                }
            }
        };
        generalRxHttpExecute(new IObservableCreator<ArrayList<User>>() {
            @Override
            public Observable<Response<ArrayList<User>>> createObservable(boolean forceNetWork) {
                return getUserService().getUserOrgs(forceNetWork, user.getLogin());
            }
        }, httpObserver, true);
    }

    public void setUser(User user) {
        this.user = user;
    }
}
