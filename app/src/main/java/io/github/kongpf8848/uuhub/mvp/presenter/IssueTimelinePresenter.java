package io.github.kongpf8848.uuhub.mvp.presenter;

import com.thirtydegreesray.dataautoaccess.annotation.AutoAccess;
import io.github.kongpf8848.uuhub.AppData;
import io.github.kongpf8848.uuhub.R;
import io.github.kongpf8848.uuhub.db.dao.DaoSession;
import io.github.kongpf8848.uuhub.http.core.HttpObserver;
import io.github.kongpf8848.uuhub.http.core.HttpResponse;
import io.github.kongpf8848.uuhub.http.error.HttpPageNoFoundError;
import io.github.kongpf8848.uuhub.mvp.contract.IIssueTimelineContract;
import io.github.kongpf8848.uuhub.mvp.model.Issue;
import io.github.kongpf8848.uuhub.mvp.model.IssueEvent;
import io.github.kongpf8848.uuhub.mvp.model.request.CommentRequestModel;
import io.github.kongpf8848.uuhub.mvp.presenter.base.BasePresenter;
import io.github.kongpf8848.uuhub.util.StringUtils;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Response;
import io.reactivex.Observable;

/**
 * Created by ThirtyDegreesRay on 2017/9/27 11:54:43
 */

public class IssueTimelinePresenter extends BasePresenter<IIssueTimelineContract.View>
        implements IIssueTimelineContract.Presenter {

    @AutoAccess Issue issue;
    private ArrayList<IssueEvent> timeline;
    private ArrayList<IssueEvent> events;

    @Inject
    public IssueTimelinePresenter(DaoSession daoSession) {
        super(daoSession);
    }

    @Override
    public void onViewInitialized() {
        super.onViewInitialized();
        loadTimeline(1, false);
    }


    @Override
    public void loadTimeline(int page, boolean isReload) {
//        if(page == 1)
//            loadEvents();
        loadComments(page, isReload);
    }

    @Override
    public boolean isEditAndDeleteEnable(int position) {
        return AppData.INSTANCE.getLoggedUser().getLogin().equals(issue.getUser().getLogin()) ||
                AppData.INSTANCE.getLoggedUser().getLogin().equals(issue.getRepoAuthorName()) ||
                AppData.INSTANCE.getLoggedUser().getLogin().equals(timeline.get(position).getUser().getLogin());
    }

    @Override
    public void deleteComment(String commentId) {
        executeSimpleRequest(getIssueService()
                .deleteComment(issue.getRepoAuthorName(), issue.getRepoName(), commentId));
    }

    @Override
    public void editComment(final String commentId, final String body) {
        HttpObserver<IssueEvent> httpObserver = new HttpObserver<IssueEvent>() {
            @Override
            public void onError(Throwable error) {
                mView.showErrorToast(getErrorTip(error));
                mView.showEditCommentPage(commentId, body);
            }

            @Override
            public void onSuccess(HttpResponse<IssueEvent> response) {
                updateComment(response.body());
                mView.showTimeline(timeline);
                mView.showSuccessToast(getString(R.string.comment_success));
            }
        };
        generalRxHttpExecute(new IObservableCreator<IssueEvent>() {
            @Override
            public Observable<Response<IssueEvent>> createObservable(boolean forceNetWork) {
                return getIssueService().editComment(issue.getRepoAuthorName(),
                        issue.getRepoName(), commentId, new CommentRequestModel(body));
            }
        }, httpObserver, false, mView.getProgressDialog(getLoadTip()));
    }

    private void loadComments(final int page, final boolean isReload){
        mView.showLoading();
        final boolean readCacheFirst = page == 1 && !isReload;
        HttpObserver<ArrayList<IssueEvent>> httpObserver
                = new HttpObserver<ArrayList<IssueEvent>>() {
            @Override
            public void onError(Throwable error) {
                mView.hideLoading();
                handleError(error);
            }

            @Override
            public void onSuccess(HttpResponse<ArrayList<IssueEvent>> response) {
                mView.hideLoading();
                ArrayList<IssueEvent> result = response.body();
                result = filterTimeLine(result);
                if (isReload || timeline == null || readCacheFirst) {
                    timeline = result;
                    timeline.add(0, getFirstComment());
                } else {
                    timeline.addAll(result);
                }
                if(response.body().size() == 0 && timeline.size() != 0){
                    mView.setCanLoadMore(false);
                }
                mView.showTimeline(timeline);
            }
        };
        generalRxHttpExecute(new IObservableCreator<ArrayList<IssueEvent>>() {
            @Override
            public Observable<Response<ArrayList<IssueEvent>>> createObservable(boolean forceNetWork) {
//                return getIssueService().getIssueComments(forceNetWork, issue.getRepoAuthorName(),
//                        issue.getRepoName(), issue.getNumber(), page);
                return getIssueService().getIssueTimeline(forceNetWork, issue.getRepoAuthorName(),
                        issue.getRepoName(), issue.getNumber(), page);
            }
        }, httpObserver, readCacheFirst);
    }

    /**
     * filter unknown event type and invalid event
     */
    private ArrayList<IssueEvent> filterTimeLine(ArrayList<IssueEvent> oriEvents){
        ArrayList<IssueEvent> filteredEvents = new ArrayList<>();
        if(oriEvents == null || oriEvents.size() == 0) return filteredEvents;
        for(IssueEvent event : oriEvents){
            if(event.getType() == null
                    //if this a issue event, actor can't be null
                    || (!IssueEvent.Type.commented.equals(event.getType()) && event.getActor() == null)) {
                continue;
            }
            filteredEvents.add(event);
        }
        return filteredEvents;
    }

    private IssueEvent getFirstComment(){
        IssueEvent firstComment = new IssueEvent();
        firstComment.setBodyHtml(issue.getBodyHtml());
        firstComment.setBody(issue.getBody());
        firstComment.setUser(issue.getUser());
        firstComment.setCreatedAt(issue.getCreatedAt());
        firstComment.setHtmlUrl(issue.getHtmlUrl());
        firstComment.setType(IssueEvent.Type.commented);
        firstComment.setParentIssue(issue);
        return firstComment;
    }

    private void handleError(Throwable error){
        if(!StringUtils.isBlankList(timeline)){
            mView.showErrorToast(getErrorTip(error));
        } else if(error instanceof HttpPageNoFoundError){
            mView.showTimeline(new ArrayList<IssueEvent>());
        } else {
            mView.showLoadError(getErrorTip(error));
        }
    }

    public ArrayList<IssueEvent> getTimeline() {
        return timeline;
    }

    private void updateComment(IssueEvent editedComment){
        for(IssueEvent event : timeline){
            if(editedComment.getId().equals(event.getId())){
                event.setBodyHtml(editedComment.getBodyHtml());
                event.setBody(editedComment.getBody());
            }
        }
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public ArrayList<String> getIssueUsersExceptMe(){
        if(timeline == null) return null;
        ArrayList<String> users = new ArrayList<String>();
        for(IssueEvent event : timeline){
            String userLoginId = event.getUser() == null ? event.getActor().getLogin() : event.getUser().getLogin();
            if(!AppData.INSTANCE.getLoggedUser().getLogin().equals(userLoginId)
                    && !users.contains(userLoginId)){
                users.add(userLoginId);
            }
        }
        return users;
    }


}
