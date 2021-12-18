package io.github.kongpf8848.uuhub.ui.activity;

import android.content.Intent;
import com.unstoppable.submitbuttonview.SubmitButton;
import butterknife.BindView;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import io.github.kongpf8848.baselib.utils.MainHandlerUtils;
import io.github.kongpf8848.githubsdk.GitHubConfig;
import io.github.kongpf8848.uuhub.R;
import io.github.kongpf8848.uuhub.inject.component.AppComponent;
import io.github.kongpf8848.uuhub.inject.component.DaggerActivityComponent;
import io.github.kongpf8848.uuhub.inject.module.ActivityModule;
import io.github.kongpf8848.uuhub.mvp.contract.ILoginContract;
import io.github.kongpf8848.uuhub.mvp.model.BasicToken;
import io.github.kongpf8848.uuhub.mvp.presenter.LoginPresenter;
import io.github.kongpf8848.uuhub.ui.activity.base.BaseActivity;
import io.github.kongpf8848.uuhub.util.AppOpener;


public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginContract.View {


    @BindView(R.id.login_bn)
    SubmitButton loginBn;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerActivityComponent.builder()
                .appComponent(appComponent)
                .activityModule(new ActivityModule(getActivity()))
                .build()
                .inject(this);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_login_new;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        mPresenter.handleOauth(intent);
        setIntent(null);
    }

    @Override
    public void onGetTokenSuccess(BasicToken basicToken) {
        loginBn.doResult(true);
        GitHubConfig.setToken(basicToken.getToken());
        mPresenter.getUserInfo(basicToken);
    }

    @Override
    public void onGetTokenError(String errorMsg) {
        loginBn.doResult(false);
        MainHandlerUtils.INSTANCE.postDelayed(() -> {
            loginBn.reset();
            loginBn.setEnabled(true);
        }, 1000);
        Toasty.error(getApplicationContext(), errorMsg).show();
    }

    @Override
    public void onLoginComplete() {
        delayFinish();
        startActivity(new Intent(getActivity(), MainActivity.class));
    }

    @OnClick(R.id.login_bn)
    public void onOauthLoginClick(){
        AppOpener.openInCustomTabsOrBrowser(getActivity(), mPresenter.getOAuth2Url());
    }

}
