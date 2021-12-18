package io.github.kongpf8848.uuhub.mvp.contract;

import io.github.kongpf8848.uuhub.mvp.contract.base.IBaseContract;

/**
 * Created by ThirtyDegreesRay on 2017/9/26 16:52:33
 */

public interface IAddIssueContract {

    interface View extends IBaseContract.View{

    }

    interface Presenter extends IBaseContract.Presenter<IAddIssueContract.View>{

    }

}
