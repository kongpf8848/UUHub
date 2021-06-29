package io.github.kongpf8848.uuhub.ui.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import android.view.View;

import io.github.kongpf8848.uuhub.R;
import io.github.kongpf8848.uuhub.inject.component.AppComponent;
import io.github.kongpf8848.uuhub.inject.component.DaggerFragmentComponent;
import io.github.kongpf8848.uuhub.inject.module.FragmentModule;
import io.github.kongpf8848.uuhub.mvp.contract.IWikiContract;
import io.github.kongpf8848.uuhub.mvp.model.WikiModel;
import io.github.kongpf8848.uuhub.mvp.presenter.WikiPresenter;
import io.github.kongpf8848.uuhub.ui.activity.ViewerActivity;
import io.github.kongpf8848.uuhub.ui.adapter.WikiAdapter;
import io.github.kongpf8848.uuhub.ui.fragment.base.ListFragment;
import io.github.kongpf8848.uuhub.util.BundleHelper;

import java.util.ArrayList;

/**
 * Created by ThirtyDegreesRay on 2017/12/6 16:43:16
 */

public class WikiFragment extends ListFragment<WikiPresenter, WikiAdapter>
        implements IWikiContract.View {

    public static WikiFragment create(@NonNull String owner, @NonNull String repo){
        WikiFragment fragment = new WikiFragment();
        fragment.setArguments(BundleHelper.builder().put("owner", owner).put("repo", repo).build());
        return fragment;
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
        setCanLoadMore(false);
    }

    @Override
    protected void onReLoadData() {
        mPresenter.loadWiki(true);
    }

    @Override
    protected String getEmptyTip() {
        return getString(R.string.no_recent_wiki_updates);
    }

    @Override
    public void showWiki(ArrayList<WikiModel> wikiList) {
        adapter.setData(wikiList);
        postNotifyDataSetChanged();
    }

    @Override
    public void onItemClick(int position, @NonNull View view) {
        super.onItemClick(position, view);
        WikiModel wikiModel = adapter.getData().get(position);
        ViewerActivity.showMdSource(getActivity(), wikiModel.getName(), wikiModel.getContentWithTitle());
    }

}
