

package io.github.kongpf8848.uuhub.mvp.contract;

import io.github.kongpf8848.uuhub.mvp.contract.base.IBaseContract;
import io.github.kongpf8848.uuhub.mvp.contract.base.IBaseListContract;
import io.github.kongpf8848.uuhub.mvp.contract.base.IBasePagerContract;
import io.github.kongpf8848.uuhub.mvp.model.User;

import java.util.ArrayList;

/**
 * Created by ThirtyDegreesRay on 2017/8/16 17:18:05
 */

public interface IUserListContract {

    interface View extends IBaseContract.View, IBasePagerContract.View, IBaseListContract.View{
        void showUsers(ArrayList<User> users);
    }

    interface Presenter extends IBasePagerContract.Presenter<IUserListContract.View>{
        void loadUsers(int page, boolean isReload);
    }

}
