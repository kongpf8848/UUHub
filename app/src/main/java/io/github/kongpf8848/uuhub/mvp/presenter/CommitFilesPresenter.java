

package io.github.kongpf8848.uuhub.mvp.presenter;

import io.github.kongpf8848.uuhub.db.dao.DaoSession;
import io.github.kongpf8848.uuhub.mvp.contract.ICommitFilesContract;
import io.github.kongpf8848.uuhub.mvp.model.CommitFile;
import io.github.kongpf8848.uuhub.mvp.model.CommitFilesPathModel;
import io.github.kongpf8848.uuhub.mvp.presenter.base.BasePresenter;
import io.github.kongpf8848.uuhub.ui.adapter.base.DoubleTypesModel;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by ThirtyDegreesRay on 2017/10/18 15:22:34
 */

public class CommitFilesPresenter extends BasePresenter<ICommitFilesContract.View>
        implements ICommitFilesContract.Presenter {

    @Inject
    public CommitFilesPresenter(DaoSession daoSession) {
        super(daoSession);
    }

    @Override
    public ArrayList<DoubleTypesModel<CommitFilesPathModel, CommitFile>> getSortedList(
            ArrayList<CommitFile> commitFiles) {
        ArrayList<DoubleTypesModel<CommitFilesPathModel, CommitFile>>  list = new ArrayList<>();
        String preBasePath = "";
        for(CommitFile commitFile : commitFiles){
            if(!preBasePath.equals(commitFile.getBasePath())){
                list.add(new DoubleTypesModel<CommitFilesPathModel, CommitFile>(
                        new CommitFilesPathModel().setPath(commitFile.getBasePath()), null));
                preBasePath = commitFile.getBasePath();
            }
            list.add(new DoubleTypesModel<CommitFilesPathModel, CommitFile>(null, commitFile));
        }
        return list;
    }
}
