

package io.github.kongpf8848.uuhub.mvp.contract;

import androidx.annotation.NonNull;

import io.github.kongpf8848.uuhub.mvp.contract.base.IBaseContract;
import io.github.kongpf8848.uuhub.mvp.contract.base.IBaseListContract;
import io.github.kongpf8848.uuhub.mvp.contract.base.IBasePagerContract;
import io.github.kongpf8848.uuhub.mvp.model.RepoCommit;

import java.util.ArrayList;

/**
 * Created by ThirtyDegreesRay on 2017/10/17 14:26:15
 */

public interface ICommitsContract {

    interface View extends IBaseContract.View, IBasePagerContract.View, IBaseListContract.View {
        void showCommits(ArrayList<RepoCommit> commits);
    }

    interface Presenter extends IBasePagerContract.Presenter<ICommitsContract.View>{
        void loadCommits(boolean isReload, int page);
    }

}
