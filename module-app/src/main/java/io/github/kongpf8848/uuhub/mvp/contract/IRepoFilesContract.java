

package io.github.kongpf8848.uuhub.mvp.contract;

import androidx.annotation.NonNull;

import io.github.kongpf8848.uuhub.mvp.contract.base.IBaseContract;
import io.github.kongpf8848.uuhub.mvp.contract.base.IBaseListContract;
import io.github.kongpf8848.uuhub.mvp.contract.base.IBasePagerContract;
import io.github.kongpf8848.uuhub.mvp.model.FileModel;
import io.github.kongpf8848.uuhub.mvp.model.FilePath;

import java.util.ArrayList;

/**
 * Created by ThirtyDegreesRay on 2017/8/14 16:02:28
 */

public interface IRepoFilesContract {

    interface View extends IBaseContract.View, IBasePagerContract.View, IBaseListContract.View{
        void showFiles(ArrayList<FileModel> files);
        void showFilePath(ArrayList<FilePath> filePath);
    }

    interface Presenter extends IBasePagerContract.Presenter<IRepoFilesContract.View>{
        void loadFiles(boolean isReload);
        void loadFiles(@NonNull String path, boolean isReload);
        boolean goBack();
        void goHome();
    }

}
