package io.github.kongpf8848.baselib.utils

import android.R
import android.content.Context
import android.content.res.Configuration
import android.util.TypedValue

object ScreenHelper {

    //获取屏幕宽度
    fun getScreenWidth(context: Context): Int {
        return context.resources.displayMetrics.widthPixels
    }

    //获取屏幕高度
    fun getScreenHeight(context: Context): Int {
        return context.resources.displayMetrics.heightPixels
    }

    //获取屏幕密度
    fun getScreenDensity(context: Context): Float {
        return context.resources.displayMetrics.density
    }


    //dp转换为px
    fun dp2px(context: Context, dp: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics).toInt()
    }

    //sp转换为px
    fun sp2px(context: Context, sp: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.resources.displayMetrics).toInt()
    }


    fun px2dp(context: Context, px: Float): Int {
        return (px / context.resources.displayMetrics.density + 0.5f).toInt()
    }

    fun px2sp(context: Context, px: Float): Int {
        return (px / context.resources.displayMetrics.scaledDensity + 0.5f).toInt()
    }


    fun getActionBarHeight(context: Context): Int {
        val tv = TypedValue()
        return if (context.theme.resolveAttribute(R.attr.actionBarSize, tv, true)) {
            TypedValue.complexToDimensionPixelSize(
                tv.data,
                context.resources.displayMetrics
            )
        } else 0
    }

    //获取状态栏高度
    fun getStatusbarHeight(context: Context): Int {
        var statusHeight = 0
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            statusHeight = context.resources.getDimensionPixelSize(resourceId)
        }
        return statusHeight
    }

    fun getNavigationBarHeight(context: Context): Int {
        val field = if (context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            "navigation_bar_height"
        } else {
            "navigation_bar_height_landscape"
        }
        val resourceId = context.resources.getIdentifier(field, "dimen", "android")
        return if (resourceId > 0) {
            context.resources.getDimensionPixelSize(resourceId)
        } else 0
    }

}