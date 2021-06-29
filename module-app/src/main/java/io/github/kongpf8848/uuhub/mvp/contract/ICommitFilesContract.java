

package io.github.kongpf8848.uuhub.mvp.contract;

import io.github.kongpf8848.uuhub.mvp.contract.base.IBaseContract;
import io.github.kongpf8848.uuhub.mvp.model.CommitFile;
import io.github.kongpf8848.uuhub.mvp.model.CommitFilesPathModel;
import io.github.kongpf8848.uuhub.ui.adapter.base.DoubleTypesModel;

import java.util.ArrayList;

/**
 * Created by ThirtyDegreesRay on 2017/10/18 15:21:28
 */

public interface ICommitFilesContract {

    interface View extends IBaseContract.View{

    }

    interface Presenter extends IBaseContract.Presenter<ICommitFilesContract.View>{
        ArrayList<DoubleTypesModel<CommitFilesPathModel, CommitFile>> getSortedList(ArrayList<CommitFile> commitFiles);
    }

}
