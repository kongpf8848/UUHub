package io.github.kongpf8848.uuhub.ui.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.core.widget.NestedScrollView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import io.github.kongpf8848.uuhub.R;
import io.github.kongpf8848.uuhub.common.GlideApp;
import io.github.kongpf8848.uuhub.inject.component.AppComponent;
import io.github.kongpf8848.uuhub.inject.component.DaggerActivityComponent;
import io.github.kongpf8848.uuhub.inject.module.ActivityModule;
import io.github.kongpf8848.uuhub.mvp.contract.IReleaseInfoContract;
import io.github.kongpf8848.uuhub.mvp.model.Release;
import io.github.kongpf8848.uuhub.mvp.presenter.ReleaseInfoPresenter;
import io.github.kongpf8848.uuhub.ui.activity.base.BaseActivity;
import io.github.kongpf8848.uuhub.ui.widget.DownloadSourceDialog;
import io.github.kongpf8848.uuhub.ui.widget.webview.CodeWebView;
import io.github.kongpf8848.uuhub.util.BundleHelper;
import io.github.kongpf8848.uuhub.util.PrefUtils;
import io.github.kongpf8848.uuhub.util.StringUtils;
import io.github.kongpf8848.uuhub.util.ViewUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ThirtyDegreesRay on 2017/9/16 13:13:46
 */

public class ReleaseInfoActivity extends BaseActivity<ReleaseInfoPresenter>
        implements IReleaseInfoContract.View {

    public static void show(Activity activity, @NonNull String owner, @NonNull String repoName,
                            @NonNull String tagName) {
        Intent intent = createIntent(activity, owner, repoName, tagName);
        activity.startActivity(intent);
    }

    public static void show(Activity activity, @NonNull String owner,
                            @NonNull String repoName, @Nullable Release release) {
        Intent intent = new Intent(activity, ReleaseInfoActivity.class);
        intent.putExtras(BundleHelper.builder().put("release", release)
                .put("owner", owner).put("repoName", repoName).build());
        activity.startActivity(intent);
    }

    public static Intent createIntent(Activity activity, @NonNull String owner, @NonNull String repoName,
                                      @NonNull String tagName) {
        return new Intent(activity, ReleaseInfoActivity.class)
                .putExtras(BundleHelper.builder()
                        .put("tagName", tagName)
                        .put("owner", owner)
                        .put("repoName", repoName).build());
    }

    @BindView(R.id.scroll_view) NestedScrollView scrollView;
    @BindView(R.id.web_view) CodeWebView webView;
    @BindView(R.id.user_avatar) ImageView userAvatar;
    @BindView(R.id.user_name) TextView userName;
    @BindView(R.id.download_bn) FloatingActionButton downloadBn;
    @BindView(R.id.loader) ProgressBar loader;

    @SuppressLint("RestrictedApi")
    @Override
    public void showReleaseInfo(Release release) {
        downloadBn.setVisibility(View.VISIBLE);
        webView.setMdSource(StringUtils.isBlank(release.getBodyHtml()) ?
                release.getBody() : release.getBodyHtml(), null);

        GlideApp.with(getActivity())
                .load(release.getAuthor().getAvatarUrl())
                .onlyRetrieveFromCache(!PrefUtils.isLoadImageEnable())
                .into(userAvatar);

        String time = StringUtils.getNewsTimeStr(getActivity(), release.getPublishedAt());
        String timeStr = "";
        if (time.contains("-")) {
            timeStr = getString(R.string.released_this)
                    .concat(" ").concat(getString(R.string.on))
                    .concat(" ").concat(time);
        } else {
            timeStr = getString(R.string.released_this)
                    .concat(" ").concat(time);
        }

        String str = release.getAuthor().getLogin().concat(" ").concat(timeStr);
        SpannableStringBuilder spannable = new SpannableStringBuilder(str);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(ViewUtils.getAccentColor(getActivity()));
        spannable.setSpan(colorSpan, 0, release.getAuthor().getLogin().length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        userName.setText(spannable);
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerActivityComponent.builder()
                .appComponent(appComponent)
                .activityModule(new ActivityModule(this))
                .build()
                .inject(this);
    }

    @Nullable
    @Override
    protected int getContentView() {
        return R.layout.activity_release_info;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setToolbarBackEnable();
        setToolbarTitle(mPresenter.getTagName(),
                mPresenter.getOwner().concat("/").concat(mPresenter.getRepoName()));
        downloadBn.setVisibility(View.GONE);
        setToolbarScrollAble(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @OnClick(R.id.download_bn)
    public void showDownloadDialog() {
        DownloadSourceDialog.show(getActivity(), mPresenter.getRepoName(),
                mPresenter.getRelease().getTagName(), mPresenter.getRelease());
    }

    @OnClick({R.id.user_name, R.id.user_avatar})
    public void onUserClick() {
        ProfileActivity.show(getActivity(), userAvatar, mPresenter.getRelease().getAuthor().getLogin(),
                mPresenter.getRelease().getAuthor().getAvatarUrl());
    }

    @Override
    public void showLoading() {
        super.showLoading();
        loader.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        super.hideLoading();
        loader.setVisibility(View.GONE);
    }

    @Override
    protected void onToolbarDoubleClick() {
        super.onToolbarDoubleClick();
        scrollView.scrollTo(0, 0);
    }
}
