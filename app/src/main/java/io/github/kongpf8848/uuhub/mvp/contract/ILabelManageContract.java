package io.github.kongpf8848.uuhub.mvp.contract;

import androidx.annotation.NonNull;

import io.github.kongpf8848.uuhub.mvp.contract.base.IBaseContract;
import io.github.kongpf8848.uuhub.mvp.contract.base.IBaseListContract;
import io.github.kongpf8848.uuhub.mvp.model.Label;

import java.util.ArrayList;

/**
 * Created by ThirtyDegreesRay on 2018/1/11 10:51:27
 */

public interface ILabelManageContract {

    interface View extends IBaseContract.View,IBaseListContract.View{
        void showLabels(ArrayList<Label> labels);
        void notifyItemInserted(int position);
        void notifyItemRemoved(int position);
        void notifyItemChanged(int position);
    }

    interface Presenter extends IBaseContract.Presenter<ILabelManageContract.View>{
        void loadLabels(boolean isReload);
        void createLabel(@NonNull Label label);
        void deleteLabel(@NonNull Label label);
        void updateLabel(@NonNull Label oriLabel, @NonNull Label newLabel);
    }

}
