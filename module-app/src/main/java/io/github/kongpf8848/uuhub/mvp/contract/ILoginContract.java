

package io.github.kongpf8848.uuhub.mvp.contract;

import android.content.Intent;

import io.github.kongpf8848.uuhub.mvp.contract.base.IBaseContract;
import io.github.kongpf8848.uuhub.mvp.model.BasicToken;


public interface ILoginContract {

    interface View extends IBaseContract.View {

        void onGetTokenSuccess(BasicToken basicToken);

        void onGetTokenError(String errorMsg);

        void onLoginComplete();

    }

    interface Presenter extends IBaseContract.Presenter<ILoginContract.View> {

        String getOAuth2Url();

        void handleOauth(Intent intent);

        void getToken(String code);

        void getUserInfo(BasicToken basicToken);

    }

}
