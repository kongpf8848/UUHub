package io.github.kongpf8848.uuhub.ui.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.View;

import io.github.kongpf8848.uuhub.R;
import io.github.kongpf8848.uuhub.inject.component.AppComponent;
import io.github.kongpf8848.uuhub.inject.component.DaggerFragmentComponent;
import io.github.kongpf8848.uuhub.inject.module.FragmentModule;
import io.github.kongpf8848.uuhub.mvp.contract.ICollectionsContract;
import io.github.kongpf8848.uuhub.mvp.model.Collection;
import io.github.kongpf8848.uuhub.mvp.presenter.CollectionsPresenter;
import io.github.kongpf8848.uuhub.ui.activity.RepoListActivity;
import io.github.kongpf8848.uuhub.ui.adapter.CollectionAdapter;
import io.github.kongpf8848.uuhub.ui.fragment.base.ListFragment;
import io.github.kongpf8848.uuhub.util.PrefUtils;

import java.util.ArrayList;

/**
 * Created by ThirtyDegreesRay on 2017/12/25 15:17:38
 */

public class CollectionsFragment extends ListFragment<CollectionsPresenter, CollectionAdapter>
        implements ICollectionsContract.View {

    public static Fragment create(){
        return new CollectionsFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list;
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
        setLoadMoreEnable(false);
    }

    @Override
    protected void onReLoadData() {
        mPresenter.loadCollections(true);
    }

    @Override
    protected String getEmptyTip() {
        return getString(R.string.no_repo_collections);
    }

    @Override
    public void showCollections(ArrayList<Collection> collections) {
        adapter.setData(collections);
        postNotifyDataSetChanged();
        if(collections != null && collections.size() > 0 && PrefUtils.isCollectionsTipAble()){
            showOperationTip(R.string.collections_tip);
            PrefUtils.set(PrefUtils.COLLECTIONS_TIP_ABLE, false);
        }
    }

    @Override
    public void onItemClick(int position, @NonNull View view) {
        super.onItemClick(position, view);
        RepoListActivity.showCollection(getActivity(), adapter.getData().get(position));
    }
}
