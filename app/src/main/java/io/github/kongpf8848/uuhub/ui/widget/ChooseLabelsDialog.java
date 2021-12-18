package io.github.kongpf8848.uuhub.ui.widget;

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.view.View;

import io.github.kongpf8848.uuhub.R;
import io.github.kongpf8848.uuhub.mvp.model.Label;
import io.github.kongpf8848.uuhub.ui.adapter.LabelManageAdapter;
import io.github.kongpf8848.uuhub.ui.adapter.base.BaseViewHolder;
import io.github.kongpf8848.uuhub.ui.adapter.base.CatchableLinearLayoutManager;

import java.util.ArrayList;

/**
 * Created by ThirtyDegreesRay on 2018/1/23 14:28:05
 */

public class ChooseLabelsDialog implements BaseViewHolder.OnItemClickListener {

    private Activity mActivity;
    private ArrayList<Label> mLabels;
    private ChooseLabelsListener mListener;

    private RecyclerView recyclerView;
    private LabelManageAdapter adapter;
    private AlertDialog dialog;

    public ChooseLabelsDialog(@NonNull Activity activity, @NonNull ArrayList<Label> labels,
                              @NonNull ChooseLabelsListener listener){
        mActivity = activity;
        mLabels = labels;
        mListener = listener;

        recyclerView = new RecyclerView(activity);
        adapter = new LabelManageAdapter(activity, null);
        recyclerView.setLayoutManager(new CatchableLinearLayoutManager(activity));
        adapter.setOnItemClickListener(this);
        adapter.setData(labels);
        recyclerView.setAdapter(adapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(activity)
                .setTitle(R.string.choose_labels)
                .setView(recyclerView)
                .setNegativeButton(R.string.cancel, null)
                .setPositiveButton(R.string.save, (dialog, which) ->
                    mListener.onChooseLabelsComplete(getChosenLabels())
                )
                .setNeutralButton(R.string.manage_labels, (dialog, which) ->
                    mListener.onShowManageLabels()
                );
        dialog = builder.create();
    }

    public void show() {
        dialog.show();
    }


    @Override
    public void onItemClick(int position, @NonNull View view) {
        adapter.getData().get(position).setSelect(!adapter.getData().get(position).isSelect());
        adapter.notifyItemChanged(position);
    }
    
    public interface ChooseLabelsListener{
        void onChooseLabelsComplete(@NonNull ArrayList<Label> labels);
        void onShowManageLabels();
    }

    private ArrayList<Label> getChosenLabels(){
        ArrayList<Label> chosenLabels = new ArrayList<>();
        for(Label label : mLabels){
            if(label.isSelect()){
                chosenLabels.add(label);
            }
        }
        return chosenLabels;
    }
    
}
