

package io.github.kongpf8848.uuhub.mvp.contract;

import io.github.kongpf8848.uuhub.mvp.contract.base.IBaseContract;
import io.github.kongpf8848.uuhub.mvp.contract.base.IBasePagerContract;
import io.github.kongpf8848.uuhub.mvp.model.User;

import java.util.ArrayList;

/**
 * Created by ThirtyDegreesRay on 2017/8/23 14:33:59
 */

public interface IProfileInfoContract {

    interface View extends IBaseContract.View, IBasePagerContract.View{
        void showProfileInfo(User user);
        void showUserOrgs(ArrayList<User> orgs);
    }

    interface Presenter extends IBasePagerContract.Presenter<IProfileInfoContract.View>{

    }

}
