package io.github.kongpf8848.uuhub.mvp.contract;

import io.github.kongpf8848.uuhub.mvp.contract.base.IBaseContract;
import io.github.kongpf8848.uuhub.mvp.contract.base.IBaseListContract;
import io.github.kongpf8848.uuhub.mvp.model.IssueEvent;

import java.util.ArrayList;

/**
 * Created by ThirtyDegreesRay on 2017/9/27 11:49:17
 */

public interface IIssueTimelineContract {

    interface View extends IBaseContract.View, IBaseListContract.View {
        void showTimeline(ArrayList<IssueEvent> events);
        void showEditCommentPage(String commentId, String body);
    }

    interface Presenter extends IBaseContract.Presenter<IIssueTimelineContract.View> {
        void loadTimeline(int page, boolean isReload);
        boolean isEditAndDeleteEnable(int position);
        void deleteComment(String commentId);
        void editComment(String commentId, String body);
    }

}
