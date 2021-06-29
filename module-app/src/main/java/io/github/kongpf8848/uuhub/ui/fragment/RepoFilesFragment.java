

package io.github.kongpf8848.uuhub.ui.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.View;

import io.github.kongpf8848.uuhub.R;
import io.github.kongpf8848.uuhub.inject.component.AppComponent;
import io.github.kongpf8848.uuhub.inject.component.DaggerFragmentComponent;
import io.github.kongpf8848.uuhub.inject.module.FragmentModule;
import io.github.kongpf8848.uuhub.mvp.contract.IRepoFilesContract;
import io.github.kongpf8848.uuhub.mvp.model.Branch;
import io.github.kongpf8848.uuhub.mvp.model.FileModel;
import io.github.kongpf8848.uuhub.mvp.model.FilePath;
import io.github.kongpf8848.uuhub.mvp.model.Repository;
import io.github.kongpf8848.uuhub.mvp.presenter.RepoFilesPresenter;
import io.github.kongpf8848.uuhub.ui.activity.RepositoryActivity;
import io.github.kongpf8848.uuhub.ui.activity.ViewerActivity;
import io.github.kongpf8848.uuhub.ui.activity.base.PagerActivity;
import io.github.kongpf8848.uuhub.ui.adapter.FilePathAdapter;
import io.github.kongpf8848.uuhub.ui.adapter.RepoFilesAdapter;
import io.github.kongpf8848.uuhub.ui.fragment.base.ListFragment;
import io.github.kongpf8848.uuhub.util.BundleHelper;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by ThirtyDegreesRay on 2017/8/14 16:13:20
 */

public class RepoFilesFragment extends ListFragment<RepoFilesPresenter, RepoFilesAdapter>
        implements IRepoFilesContract.View, PagerActivity.IFragmentKeyListener,
        RepositoryActivity.RepositoryListener{

    public static RepoFilesFragment create(Repository repository) {
        RepoFilesFragment fragment = new RepoFilesFragment();
        fragment.setArguments(BundleHelper.builder().put("repo", repository).build());
        return fragment;
    }

    @BindView(R.id.path_recycler_view)
    RecyclerView pathRecyclerView;
    @Inject FilePathAdapter filePathAdapter;

    @Override
    public void showFiles(ArrayList<FileModel> files) {
        adapter.setData(files);
        postNotifyDataSetChanged();
    }

    @Override
    public void showFilePath(final ArrayList<FilePath> filePath) {
        filePathAdapter.setData(filePath);
        filePathAdapter.notifyDataSetChanged();
        if(filePath.size() != 0)
            pathRecyclerView.post(new Runnable() {
                @Override
                public void run() {
                    pathRecyclerView.smoothScrollToPosition(filePath.size() - 1);
                }
            });
    }

    @Override
    public void showLoading() {
        refreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_repo_files;
    }

    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {
        DaggerFragmentComponent.builder()
                .appComponent(appComponent)
                .fragmentModule(new FragmentModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        super.initFragment(savedInstanceState);
        pathRecyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        pathRecyclerView.setAdapter(filePathAdapter);
        filePathAdapter.setOnItemClickListener((position, view) ->
                mPresenter.loadFiles(filePathAdapter.getData().get(position).getFullPath(), false)
        );
    }

    @Override
    protected void onReLoadData() {
        mPresenter.loadFiles(true);
    }

    @Override
    protected String getEmptyTip() {
        return getString(R.string.no_file);
    }

    @Override
    public void onItemClick(int position, @NonNull View view) {
        super.onItemClick(position, view);
        FileModel model = adapter.getData().get(position);
        if(model.isDir()){
            mPresenter.loadFiles(model.getPath(), false);
        }else{
            ViewerActivity.show(getActivity(), model, mPresenter.getRepoName());
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            return mPresenter.goBack();
        }
        return false;
    }

    @Override
    public void onFragmentShowed() {
        super.onFragmentShowed();
        if(mPresenter != null) mPresenter.prepareLoadData();
    }

    @Override
    public void onRepositoryInfoUpdated(Repository repository) {

    }

    @Override
    public void onBranchChanged(Branch branch) {
        if(mPresenter == null){
            getArguments().putString("branch", branch.getName());
        } else {
            mPresenter.setCurBranch(branch.getName());
            mPresenter.setLoaded(false);
            mPresenter.prepareLoadData();
        }
    }
}
