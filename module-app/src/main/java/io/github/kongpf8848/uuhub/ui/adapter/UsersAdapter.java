

package io.github.kongpf8848.uuhub.ui.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import io.github.kongpf8848.uuhub.R;
import io.github.kongpf8848.uuhub.common.GlideApp;
import io.github.kongpf8848.uuhub.mvp.model.User;
import io.github.kongpf8848.uuhub.ui.adapter.base.BaseAdapter;
import io.github.kongpf8848.uuhub.ui.adapter.base.BaseViewHolder;
import io.github.kongpf8848.uuhub.ui.fragment.base.BaseFragment;
import io.github.kongpf8848.uuhub.util.PrefUtils;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by ThirtyDegreesRay on 2017/8/16 17:12:05
 */

public class UsersAdapter extends BaseAdapter<UsersAdapter.ViewHolder, User> {

    private boolean cardEnable = true;

    @Inject
    public UsersAdapter(Context context, BaseFragment fragment){
        super(context, fragment);
    }

    public void setCardEnable(boolean cardEnable) {
        this.cardEnable = cardEnable;
    }

    @Override
    protected int getLayoutId(int viewType) {
        return cardEnable ? R.layout.layout_item_user : R.layout.layout_item_user_no_card;
    }

    @Override
    protected ViewHolder getViewHolder(View itemView, int viewType) {
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        GlideApp.with(fragment)
                .load(data.get(position).getAvatarUrl())
                .onlyRetrieveFromCache(!PrefUtils.isLoadImageEnable())
                .into(holder.avatar);
        holder.name.setText(data.get(position).getLogin());
    }

    class ViewHolder extends BaseViewHolder {
        @BindView(R.id.avatar) ImageView avatar;
        @BindView(R.id.name) TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
