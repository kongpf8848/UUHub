

package io.github.kongpf8848.uuhub.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageView;

import io.github.kongpf8848.uuhub.R;
import io.github.kongpf8848.uuhub.util.StringUtils;
import io.github.kongpf8848.uuhub.util.WindowUtil;

/**
 * Created by ThirtyDegreesRay on 2017/10/11 11:05:36
 * A ImageButton which can toast text when long click.
 */

public class ToastAbleImageButton extends AppCompatImageView
        implements View.OnLongClickListener {

    private String toastText ;

    public ToastAbleImageButton(Context context) {
        super(context);
        init(null);
    }

    public ToastAbleImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ToastAbleImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray tp = getContext().obtainStyledAttributes(attrs, R.styleable.ToastAbleImageButton);
            try {
                toastText = tp.getString(R.styleable.ToastAbleImageButton_toast_text);
            } finally {
                tp.recycle();
            }
            if(!StringUtils.isBlank(toastText)){
                setOnLongClickListener(this);
            }
        }
    }

    @Override
    public boolean onLongClick(View v) {
        Toast toast = Toast.makeText(getContext(), toastText, Toast.LENGTH_SHORT);
        int[] location = new int[2];
        v.getLocationInWindow(location);
        if(location[0] + v.getWidth() / 2 < WindowUtil.screenWidth / 2){
            toast.setGravity(Gravity.TOP|Gravity.START, location[0] + v.getWidth() , location[1] + v.getHeight() / 2);
        } else {
            toast.setGravity(Gravity.TOP|Gravity.END, WindowUtil.screenWidth - location[0] , location[1] + v.getHeight() / 2);
        }
        toast.show();
        return true;
    }

}
