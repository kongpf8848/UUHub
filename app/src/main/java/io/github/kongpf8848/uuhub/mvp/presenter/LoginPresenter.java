package io.github.kongpf8848.uuhub.mvp.presenter;

import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;

import java.util.Date;
import java.util.UUID;

import javax.inject.Inject;

import io.github.kongpf8848.baselib.utils.DateHelper;
import io.github.kongpf8848.githubsdk.GitHubSdk;
import io.github.kongpf8848.githubsdk.http.GitHubHttpCallback;
import io.github.kongpf8848.githubsdk.model.OauthToken;
import io.github.kongpf8848.uuhub.AppConfig;
import io.github.kongpf8848.uuhub.AppData;
import io.github.kongpf8848.uuhub.db.entity.AuthUser;
import io.github.kongpf8848.uuhub.db.dao.AuthUserDao;
import io.github.kongpf8848.uuhub.db.dao.DaoSession;
import io.github.kongpf8848.uuhub.mvp.contract.ILoginContract;
import io.github.kongpf8848.uuhub.mvp.model.BasicToken;
import io.github.kongpf8848.uuhub.mvp.model.User;
import io.github.kongpf8848.uuhub.mvp.presenter.base.BasePresenter;
import io.github.kongpf8848.uuhub.util.StringUtils;


public class LoginPresenter extends BasePresenter<ILoginContract.View> implements ILoginContract.Presenter {

    @Inject
    public LoginPresenter(DaoSession daoSession) {
        super(daoSession);
    }

    @NonNull
    @Override
    public String getOAuth2Url() {
        String state = UUID.randomUUID().toString();
        return String.format("%s?client_id=%s&scope=%s&state=%s",AppConfig.OAUTH2_URL,AppConfig.OPENHUB_CLIENT_ID,AppConfig.OAUTH2_SCOPE,state);
    }


    @Override
    public void handleOauth(Intent intent) {
        Uri uri = intent.getData();
        if (uri != null) {
            String code = uri.getQueryParameter("code");
            getToken(code);
        }
    }


    @Override
    public void getToken(String code) {

        GitHubSdk.getInstance().getAccessToken(AppConfig.OPENHUB_CLIENT_ID, AppConfig.OPENHUB_CLIENT_SECRET, code, new GitHubHttpCallback<OauthToken>() {

            @Override
            public void onStart() {
                super.onStart();
                mView.showProgressDialog(getLoadTip());
            }

            @Override
            public void onNext(OauthToken response) {
                mView.onGetTokenSuccess(BasicToken.generateFromOauthToken(response));
            }

            @Override
            public void onError(Throwable e) {
                mView.dismissProgressDialog();
                mView.showErrorToast(getErrorTip(e));
            }
        });
    }


    @Override
    public void getUserInfo(final BasicToken basicToken) {

        GitHubSdk.getInstance().getUserInfo(new GitHubHttpCallback<io.github.kongpf8848.githubsdk.model.User>() {
            @Override
            public void onStart() {
                super.onStart();
                mView.showProgressDialog(getLoadTip());
            }

            @Override
            public void onNext(io.github.kongpf8848.githubsdk.model.User response) {
                User user = new User();
                user.setLogin(response.getLogin());
                user.setId(String.valueOf(response.getId()));
                user.setAvatarUrl(response.getAvatarUrl());
                user.setName(response.getName());
                user.setBio(response.getBio());
                user.setFollowers(response.getFollowers());
                user.setFollowing(response.getFollowing());
                user.setBlog(response.getBlog());
                user.setEmail(response.getEmail());
                user.setType(response.getType().equals("User") ? User.UserType.User : User.UserType.Organization);
                user.setHtmlUrl(response.getHtmlUrl());
                user.setLocation(response.getLocation());
                user.setCompany(response.getCompany());
                user.setPublicRepos(response.getPublicRepos());
                user.setPublicGists(response.getPublicGists());
                user.setCreatedAt(DateHelper.INSTANCE.toDate(response.getCreatedAt(), "yyyy-MM-dd'T'HH:mm:ss'Z'"));
                user.setUpdatedAt(DateHelper.INSTANCE.toDate(response.getUpdatedAt(), "yyyy-MM-dd'T'HH:mm:ss'Z'"));

                saveAuthUser(basicToken, user);
                mView.onLoginComplete();
            }

            @Override
            public void onError(Throwable e) {
                mView.dismissProgressDialog();
                mView.onGetTokenError(getErrorTip(e));
            }
        });

    }

    private void saveAuthUser(BasicToken basicToken, User userInfo) {
        String updateSql = "UPDATE " + daoSession.getAuthUserDao().getTablename()
                + " SET " + AuthUserDao.Properties.Selected.columnName + " = 0";
        daoSession.getAuthUserDao().getDatabase().execSQL(updateSql);

        String deleteExistsSql = "DELETE FROM " + daoSession.getAuthUserDao().getTablename()
                + " WHERE " + AuthUserDao.Properties.LoginId.columnName
                + " = '" + userInfo.getLogin() + "'";
        daoSession.getAuthUserDao().getDatabase().execSQL(deleteExistsSql);

        AuthUser authUser = new AuthUser();
        String scope = StringUtils.listToString(basicToken.getScopes(), ",");
        Date date = new Date();
        authUser.setAccessToken(basicToken.getToken());
        authUser.setScope(scope);
        authUser.setAuthTime(date);
        authUser.setExpireIn(360 * 24 * 60 * 60);
        authUser.setSelected(true);
        authUser.setLoginId(userInfo.getLogin());
        authUser.setName(userInfo.getName());
        authUser.setAvatar(userInfo.getAvatarUrl());
        daoSession.getAuthUserDao().insert(authUser);

        AppData.INSTANCE.setAuthUser(authUser);
        AppData.INSTANCE.setLoggedUser(userInfo);
    }


}
