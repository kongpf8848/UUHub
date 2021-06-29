

package io.github.kongpf8848.uuhub.ui.activity.base;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import io.github.kongpf8848.uuhub.R;
import io.github.kongpf8848.uuhub.mvp.contract.base.IBaseContract;
import io.github.kongpf8848.uuhub.ui.adapter.base.BaseAdapter;
import io.github.kongpf8848.uuhub.ui.adapter.base.BaseViewHolder;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created on 2017/8/1.
 *
 * @author ThirtyDegreesRay
 */

public abstract class ListActivity <P extends IBaseContract.Presenter, A extends BaseAdapter>
        extends BaseActivity<P> implements IBaseContract.View,
        BaseViewHolder.OnItemClickListener,
        BaseViewHolder.OnItemLongClickListener {

    @BindView(R.id.recycler_view) protected RecyclerView recyclerView;
    @Inject protected A adapter;

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter.setOnItemLongClickListener(this);
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position, @NonNull View view) {

    }

    @Override
    public boolean onItemLongClick(int position, @NonNull View view) {
        return false;
    }

}
