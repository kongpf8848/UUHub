
package io.github.kongpf8848.baselib.swipeback;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.customview.widget.ViewDragHelper;

import io.github.kongpf8848.baselib.utils.ActivityContainer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class SwipBacActivity extends AppCompatActivity implements SwipeBackLayout.SwipeListener {

    protected SwipeBackLayout mSwipeBackLayout;
    public static final int SMOOTH_WIDTH = 50;
    private final int ALPHA_COLOR = 0x60ffffff;

    /**
     * 8.0 bug
     *
     * @param requestedOrientation
     */
    @Override
    public void setRequestedOrientation(int requestedOrientation) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O && isTranslucentOrFloating()) {
            return;
        }
        super.setRequestedOrientation(requestedOrientation);
    }

    private boolean isTranslucentOrFloating() {
        boolean isTranslucentOrFloating = false;
        try {
            int[] styleableRes = (int[]) Class.forName("com.android.internal.R$styleable").getField("Window").get(null);
            final TypedArray ta = obtainStyledAttributes(styleableRes);
            Method m = ActivityInfo.class.getMethod("isTranslucentOrFloating", TypedArray.class);
            m.setAccessible(true);
            isTranslucentOrFloating = (boolean) m.invoke(null, ta);
            m.setAccessible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isTranslucentOrFloating;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O && isTranslucentOrFloating()) {
            fixOrientation();
        }
        super.onCreate(savedInstanceState);
        ActivityContainer.INSTANCE.add(this);

        mSwipeBackLayout = new SwipeBackLayout(this);
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        mSwipeBackLayout.setEdgeSize(dip2px(SMOOTH_WIDTH));
        //mSwipeBackLayout.setScrimColor(ALPHA_COLOR);

        if (canUseSwipeBackLayout()) {
            mSwipeBackLayout.setEnableGesture(true);
        } else {
            mSwipeBackLayout.setEnableGesture(false);
        }
        mSwipeBackLayout.setSwipeListener(this);

    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (canUseSwipeBackLayout()) {
            if (mSwipeBackLayout != null) {
                mSwipeBackLayout.attachToActivity(this);
            }
        }
    }

    @Override
    public <T extends View> T findViewById(int id) {
        T v = super.findViewById(id);
        if (v != null)
            return v;
        if (canUseSwipeBackLayout()) {
            if (mSwipeBackLayout != null) {
                mSwipeBackLayout.findViewById(id);
            }
        }
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityContainer.INSTANCE.remove(this);
    }

    @Override
    public void finish() {
        if (canUseSwipeBackLayout()) {
            onScrollStateChange(ViewDragHelper.STATE_IDLE,1.0f);
        }
        super.finish();
    }

    private boolean canSwipeBack = true;

    public boolean canUseSwipeBackLayout() {
        return canSwipeBack;
    }

    public void setCanSwipeBack(boolean canSwipeBack) {
        this.canSwipeBack = canSwipeBack;
        mSwipeBackLayout.setEnableGesture(canSwipeBack);
    }

    @Override
    public void onScrollStateChange(int state, float scrollPercent) {
        if(scrollPercent>=0.0f && scrollPercent<=1.0f){
            Activity activity= ActivityContainer.INSTANCE.getPenultimateActivity();
            if (activity != null) {
                View decorView = activity.getWindow().getDecorView();
                decorView.setTranslationX(-decorView.getMeasuredWidth() * 0.25f * (1 - scrollPercent));
            }
        }
    }

    @Override
    public void onEdgeTouch(int edgeFlag) {
    }

    @Override
    public void onScrollOverThreshold() {

    }

    public static int dip2px(float dpValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    private boolean fixOrientation() {
        try {
            Field field = Activity.class.getDeclaredField("mActivityInfo");
            field.setAccessible(true);
            ActivityInfo o = (ActivityInfo) field.get(this);
            o.screenOrientation =ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED;
            field.setAccessible(false);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
