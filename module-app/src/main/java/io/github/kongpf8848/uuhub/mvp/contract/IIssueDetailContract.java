package io.github.kongpf8848.uuhub.mvp.contract;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import io.github.kongpf8848.uuhub.mvp.contract.base.IBaseContract;
import io.github.kongpf8848.uuhub.mvp.model.Issue;
import io.github.kongpf8848.uuhub.mvp.model.IssueEvent;

/**
 * Created by ThirtyDegreesRay on 2017/9/26 16:18:18
 */

public interface IIssueDetailContract {

    interface View extends IBaseContract.View{
        void showIssue(@NonNull Issue issue);
        void showAddedComment(@NonNull IssueEvent event);
        void showAddCommentPage(@Nullable String text);
    }

    interface Presenter extends IBaseContract.Presenter<IIssueDetailContract.View> {
        void addComment(@NonNull String text);
        void toggleIssueState();
    }

}
