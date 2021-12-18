package io.github.kongpf8848.uuhub.ui.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;

import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.thirtydegreesray.dataautoaccess.annotation.AutoAccess;
import io.github.kongpf8848.uuhub.R;
import io.github.kongpf8848.uuhub.mvp.contract.base.IBaseContract;
import io.github.kongpf8848.uuhub.ui.activity.base.SingleFragmentActivity;
import io.github.kongpf8848.uuhub.ui.fragment.LabelManageFragment;
import io.github.kongpf8848.uuhub.util.BundleHelper;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ThirtyDegreesRay on 2018/1/11 10:50:09
 */

public class LabelManageActivity extends SingleFragmentActivity<IBaseContract.Presenter, LabelManageFragment> {

    public static void show(@NonNull Activity activity, @NonNull String owner, @NonNull String repo){
        Intent intent = new Intent(activity, LabelManageActivity.class);
        intent.putExtras(BundleHelper.builder().put("owner", owner).put("repo", repo).build());
        activity.startActivity(intent);
    }

    @AutoAccess String owner;
    @AutoAccess String repo;
    @BindView(R.id.float_action_bn)
    FloatingActionButton floatingActionButton;


    @Override
    protected LabelManageFragment createFragment() {
        return LabelManageFragment.create(owner, repo);
    }

    @SuppressLint("RestrictedApi")
    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setToolbarTitle(getString(R.string.labels), owner + "/" + repo);
        floatingActionButton.setVisibility(View.VISIBLE);
        setToolbarScrollAble(true);
    }

    @OnClick(R.id.float_action_bn) public void onCreateLabelClick(){
        getFragment().onCreateLabelClick();
    }

}
