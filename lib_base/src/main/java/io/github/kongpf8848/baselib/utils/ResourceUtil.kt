package io.github.kongpf8848.baselib.utils

import android.content.Context
import java.lang.Exception

object ResourceUtil {

    fun getId(context: Context, resName: String): Int {
        return getResId(context, resName, "id")
    }

    fun getStringId(context: Context, resName: String): Int {
        return getResId(context, resName, "string")
    }

    fun getMipmapId(context: Context, resName: String): Int {
        return getResId(context, resName, "mipmap")
    }

    fun getDrawableId(context: Context, resName: String): Int {
        return getResId(context, resName, "drawable")
    }

    fun getLayoutId(context: Context, resName: String): Int {
        return getResId(context, resName, "layout")
    }

    fun getStyleId(context: Context, resName: String): Int {
        return getResId(context, resName, "style")
    }

    fun getColorId(context: Context, resName: String): Int {
        return getResId(context, resName, "color")
    }

    fun getDimenId(context: Context, resName: String): Int {
        return getResId(context, resName, "dimen")
    }

    fun getAnimId(context: Context, resName: String): Int {
        return getResId(context, resName, "anim")
    }

    fun getMenuId(context: Context, resName: String): Int {
        return getResId(context, resName, "menu")
    }

    private fun getResId(context: Context, resName: String, defType: String): Int {
        return try {
            context.resources.getIdentifier(resName, defType, context.packageName)
        } catch (e: Exception) {
            -1
        }
    }


}