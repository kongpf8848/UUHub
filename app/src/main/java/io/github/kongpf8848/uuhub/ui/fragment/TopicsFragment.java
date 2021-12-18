package io.github.kongpf8848.uuhub.ui.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.View;

import io.github.kongpf8848.uuhub.R;
import io.github.kongpf8848.uuhub.inject.component.AppComponent;
import io.github.kongpf8848.uuhub.inject.component.DaggerFragmentComponent;
import io.github.kongpf8848.uuhub.inject.module.FragmentModule;
import io.github.kongpf8848.uuhub.mvp.contract.ITopicsContract;
import io.github.kongpf8848.uuhub.mvp.model.Topic;
import io.github.kongpf8848.uuhub.mvp.presenter.TopicsPresenter;
import io.github.kongpf8848.uuhub.ui.activity.RepoListActivity;
import io.github.kongpf8848.uuhub.ui.adapter.TopicsAdapter;
import io.github.kongpf8848.uuhub.ui.fragment.base.ListFragment;
import io.github.kongpf8848.uuhub.util.PrefUtils;

import java.util.ArrayList;

/**
 * Created by ThirtyDegreesRay on 2017/12/29 11:12:41
 */

public class TopicsFragment extends ListFragment<TopicsPresenter, TopicsAdapter>
        implements ITopicsContract.View {

    public static Fragment create(){
        return new TopicsFragment();
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        super.initFragment(savedInstanceState);
        setLoadMoreEnable(false);
    }

    @Override
    public void showTopics(ArrayList<Topic> topics) {
        adapter.setData(topics);
        postNotifyDataSetChanged();
        if(topics != null && topics.size() > 0 && PrefUtils.isTopicsTipEnable()){
            showOperationTip(R.string.topics_tip);
            PrefUtils.set(PrefUtils.TOPICS_TIP_ABLE, false);
        }
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
    protected void onReLoadData() {
        mPresenter.loadTopics(true);
    }

    @Override
    protected String getEmptyTip() {
        return getString(R.string.no_topics);
    }

    @Override
    public void onItemClick(int position, @NonNull View view) {
        super.onItemClick(position, view);
        RepoListActivity.showTopic(getActivity(), adapter.getData().get(position));
    }
}
