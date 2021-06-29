package io.github.kongpf8848.baselib.utils

import android.content.Context
import android.os.Environment
import java.io.File

object StorageHelper {
    /**
     * 获取内部沙盒文件目录
     * /data/data/xxx/files
     *
     * @param context
     * @return
     */
    fun getInternalFilesPath(context: Context): String {
        return context.filesDir.absolutePath
    }

    /**
     * 获取内部沙盒缓存目录
     * /data/data/xxx/cache
     *
     * @param context
     * @return
     */
    fun getInternalCachePath(context: Context): String {
        return context.cacheDir.absolutePath
    }

    /**
     * 获取外部沙盒文件根目录
     * /storage/emulated/0/Android/data/xxx/files
     *
     * @param context
     * @return
     */
    fun getExternalSandBoxFilesPath(context: Context): String {
        return getExternalSandBoxPath(context, null)
    }

    fun getExternalSandBoxPath(
        context: Context,
        type: String?
    ): String {
        return context.getExternalFilesDir(type)!!.absolutePath
    }

    /**
     * 获取外部沙盒缓存目录
     * /storage/emulated/0/Android/data/xxx/cache
     *
     * @param context
     * @return
     */
    fun getExternalSandBoxCachePath(context: Context): String {
        return context.externalCacheDir!!.absolutePath
    }

    /**
     * 外部公共目录
     *
     * @param type
     * @return
     */
    fun getExternalStoragePublicPath(type: String?): String {
        return Environment.getExternalStoragePublicDirectory(type).absolutePath
    }

    /**
     * 外部公共目录文件
     *
     * @param type
     * @return
     */
    fun getExternalStoragePublicDirectory(type: String?): File {
        return Environment.getExternalStoragePublicDirectory(type)
    }

    /**
     * 是否有外部存储(SD卡)
     * @return
     */
    fun hasExternalStorage(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }

    /**
     * 程序中图片缓存目录：用户头像选择头像裁剪临时文件
     * @param context
     * @return
     */
    fun getCroppedImageTmpFile(context: Context): File {
        val dir = File(context.externalCacheDir, "croppedImages")
        if (!dir.exists()) {
            dir.mkdirs()
        }
        val file = File(dir, "head-" + System.currentTimeMillis())
        if (file.exists()) {
            file.delete()
        }
        return file
    }
}