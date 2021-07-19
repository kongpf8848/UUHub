package io.github.kongpf8848.baselib.utils

import android.R
import android.app.Activity
import android.app.Dialog
import android.view.View
import android.view.Window
import androidx.annotation.IdRes

object ViewUtils {

    inline fun <reified T : View> getView(v: View, @IdRes resId: Int): T {
        return v.findViewById<View>(resId) as T
    }

    inline fun <reified T : View> getContentView(window: Window): T {
        return getView(window.decorView, R.id.content)
    }

    inline fun <reified T : View> getView(d: Dialog, @IdRes resId: Int): T {
        return d.findViewById<View>(resId) as T
    }

    inline fun <reified T : View> getView(activity: Activity, @IdRes resId: Int): T {
        return activity.findViewById<View>(resId) as T
    }

}