

package io.github.kongpf8848.uuhub.inject.component;

import io.github.kongpf8848.uuhub.inject.FragmentScope;
import io.github.kongpf8848.uuhub.inject.module.FragmentModule;
import io.github.kongpf8848.uuhub.ui.fragment.CollectionsFragment;
import io.github.kongpf8848.uuhub.ui.fragment.LabelManageFragment;
import io.github.kongpf8848.uuhub.ui.fragment.LanguagesEditorFragment;
import io.github.kongpf8848.uuhub.ui.fragment.ActivityFragment;
import io.github.kongpf8848.uuhub.ui.fragment.BookmarksFragment;
import io.github.kongpf8848.uuhub.ui.fragment.CommitFilesFragment;
import io.github.kongpf8848.uuhub.ui.fragment.CommitsFragment;
import io.github.kongpf8848.uuhub.ui.fragment.IssueTimelineFragment;
import io.github.kongpf8848.uuhub.ui.fragment.IssuesFragment;
import io.github.kongpf8848.uuhub.ui.fragment.NotificationsFragment;
import io.github.kongpf8848.uuhub.ui.fragment.ProfileInfoFragment;
import io.github.kongpf8848.uuhub.ui.fragment.ReleasesFragment;
import io.github.kongpf8848.uuhub.ui.fragment.RepoFilesFragment;
import io.github.kongpf8848.uuhub.ui.fragment.RepoInfoFragment;
import io.github.kongpf8848.uuhub.ui.fragment.RepositoriesFragment;
import io.github.kongpf8848.uuhub.ui.fragment.TopicsFragment;
import io.github.kongpf8848.uuhub.ui.fragment.TraceFragment;
import io.github.kongpf8848.uuhub.ui.fragment.UserListFragment;
import io.github.kongpf8848.uuhub.ui.fragment.ViewerFragment;
import io.github.kongpf8848.uuhub.ui.fragment.WikiFragment;

import dagger.Component;

/**
 * Created on 2017/7/18.
 *
 * @author ThirtyDegreesRay
 */

@FragmentScope
@Component(modules = FragmentModule.class, dependencies = AppComponent.class)
public interface FragmentComponent {
    void inject(RepositoriesFragment fragment);
    void inject(RepoInfoFragment fragment);
    void inject(RepoFilesFragment fragment);
    void inject(UserListFragment fragment);
    void inject(ViewerFragment fragment);
    void inject(ProfileInfoFragment fragment);
    void inject(ActivityFragment fragment);
    void inject(ReleasesFragment fragment);
    void inject(IssuesFragment fragment);
    void inject(IssueTimelineFragment fragment);
    void inject(CommitsFragment fragment);
    void inject(CommitFilesFragment fragment);
    void inject(NotificationsFragment fragment);
    void inject(BookmarksFragment fragment);
    void inject(TraceFragment fragment);
    void inject(LanguagesEditorFragment fragment);
    void inject(WikiFragment fragment);
    void inject(CollectionsFragment fragment);
    void inject(TopicsFragment fragment);
    void inject(LabelManageFragment fragment);
}
