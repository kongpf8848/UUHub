

package io.github.kongpf8848.uuhub.mvp.contract;

import androidx.annotation.NonNull;

import io.github.kongpf8848.uuhub.mvp.contract.base.IBaseContract;
import io.github.kongpf8848.uuhub.mvp.contract.base.IBaseListContract;
import io.github.kongpf8848.uuhub.mvp.contract.base.IBasePagerContract;
import io.github.kongpf8848.uuhub.mvp.model.Notification;
import io.github.kongpf8848.uuhub.mvp.model.Repository;
import io.github.kongpf8848.uuhub.ui.adapter.base.DoubleTypesModel;

import java.util.ArrayList;

/**
 * Created by ThirtyDegreesRay on 2017/11/6 17:44:57
 */

public interface INotificationsContract {

    interface View extends IBaseContract.View, IBasePagerContract.View, IBaseListContract.View{
        void showNotifications(ArrayList<DoubleTypesModel<Repository, Notification>> notifications);
    }

    interface Presenter extends IBasePagerContract.Presenter<INotificationsContract.View> {
        void loadNotifications(int page, boolean isReload);
        void markNotificationAsRead(String threadId);
        void markAllNotificationsAsRead();
        boolean isNotificationsAllRead();
        void markRepoNotificationsAsRead(@NonNull Repository repository);
    }

}
