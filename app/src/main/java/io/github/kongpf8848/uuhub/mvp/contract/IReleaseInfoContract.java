package io.github.kongpf8848.uuhub.mvp.contract;

import io.github.kongpf8848.uuhub.mvp.contract.base.IBaseContract;
import io.github.kongpf8848.uuhub.mvp.model.Release;

/**
 * Created by ThirtyDegreesRay on 2017/9/16 13:09:26
 */

public interface IReleaseInfoContract {

    interface View extends IBaseContract.View{
        void showReleaseInfo(Release release);
    }

    interface Presenter extends IBaseContract.Presenter<IReleaseInfoContract.View>{

    }

}
