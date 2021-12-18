

package io.github.kongpf8848.uuhub.inject.component;

import io.github.kongpf8848.uuhub.inject.ActivityScope;
import io.github.kongpf8848.uuhub.inject.module.ActivityModule;
import io.github.kongpf8848.uuhub.ui.activity.CommitDetailActivity;
import io.github.kongpf8848.uuhub.ui.activity.EditIssueActivity;
import io.github.kongpf8848.uuhub.ui.activity.IssueDetailActivity;
import io.github.kongpf8848.uuhub.ui.activity.IssuesActivity;
import io.github.kongpf8848.uuhub.ui.activity.LoginActivity;
import io.github.kongpf8848.uuhub.ui.activity.MainActivity;
import io.github.kongpf8848.uuhub.ui.activity.ProfileActivity;
import io.github.kongpf8848.uuhub.ui.activity.ReleaseInfoActivity;
import io.github.kongpf8848.uuhub.ui.activity.RepositoryActivity;
import io.github.kongpf8848.uuhub.ui.activity.SearchActivity;
import io.github.kongpf8848.uuhub.ui.activity.SettingsActivity;
import io.github.kongpf8848.uuhub.ui.activity.SplashActivity;
import io.github.kongpf8848.uuhub.ui.activity.TrendingActivity;

import dagger.Component;

/**
 * ActivityComponent
 * Created by ThirtyDegreesRay on 2016/8/30 14:56
 */
@ActivityScope
@Component(modules = ActivityModule.class, dependencies = AppComponent.class)
public interface ActivityComponent {
    void inject(SplashActivity activity);
    void inject(LoginActivity activity);
    void inject(MainActivity activity);
    void inject(SettingsActivity activity);
    void inject(RepositoryActivity activity);
    void inject(ProfileActivity activity);
    void inject(SearchActivity activity);
    void inject(ReleaseInfoActivity activity);
    void inject(IssuesActivity activity);
    void inject(IssueDetailActivity activity);
    void inject(EditIssueActivity activity);
    void inject(CommitDetailActivity activity);
    void inject(TrendingActivity activity);
}
