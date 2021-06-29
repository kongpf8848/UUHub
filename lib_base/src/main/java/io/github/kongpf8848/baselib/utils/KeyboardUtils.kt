package io.github.kongpf8848.baselib.utils

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.view.View
import android.view.inputmethod.InputMethodManager

object KeyboardUtils {
    /**
     * 显示键盘
     *
     * @param view
     */
    fun showSoftInput(view: View?) {
        if (view != null) {
            view.isFocusable = true
            view.isFocusableInTouchMode = true
            view.requestFocus()
            val imm =
                view.context.applicationContext
                    .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    /**
     * 隐藏键盘
     *
     * @param activity
     */
    fun hideSoftInput(activity: Activity?) {
        if (activity != null) {
            hideSoftInput(activity.currentFocus)
        }
    }

    /**
     * 隐藏键盘
     *
     */
    fun toggleSoftInput(context: Context) {
        val inputMgr =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMgr.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
    }

    fun isKeyboardActive(
        context: Context,
        view: View?
    ): Boolean {
        val imm = context
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return imm.isActive(view)
    }

    fun hideSoftInput(view: View?) {
        if (view != null) {
            val imm =
                view.context.applicationContext
                    .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun getKeyboardHeight(contentView: View): Int {
        val r = Rect()
        contentView.getWindowVisibleDisplayFrame(r)
        return contentView.rootView.height - r.height()
    }
}