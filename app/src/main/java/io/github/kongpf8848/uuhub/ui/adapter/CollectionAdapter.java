package io.github.kongpf8848.uuhub.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import io.github.kongpf8848.uuhub.R;
import io.github.kongpf8848.uuhub.mvp.model.Collection;
import io.github.kongpf8848.uuhub.ui.adapter.base.BaseAdapter;
import io.github.kongpf8848.uuhub.ui.adapter.base.BaseViewHolder;
import io.github.kongpf8848.uuhub.ui.fragment.base.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by ThirtyDegreesRay on 2017/12/25 15:07:58
 */

public class CollectionAdapter extends BaseAdapter<CollectionAdapter.ViewHolder, Collection> {

    @Inject
    public CollectionAdapter(Context context, BaseFragment fragment) {
        super(context, fragment);
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.layout_item_collectioin;
    }

    @Override
    protected ViewHolder getViewHolder(View itemView, int viewType) {
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        Collection model = data.get(position);
        holder.name.setText(model.getName());
        holder.desc.setText(model.getDesc());
    }

    class ViewHolder extends BaseViewHolder {
        @BindView(R.id.name) TextView name;
        @BindView(R.id.desc) TextView desc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
