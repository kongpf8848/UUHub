package io.github.kongpf8848.baselib.utils

import android.content.Context
import androidx.annotation.RawRes
import com.google.gson.reflect.TypeToken
import java.io.*

object AssetHelper {

    /**
     * 读取src/main/assets目录下json文件,转换成对应的bean
     */
    inline fun <reified T> readJsonFromAssets(context: Context, fileName: String): T {
        val inputStream = context.assets.open(fileName)
        val json = String(inputStream.readBytes())
        inputStream.close()
        return GsonHelper.fromJson(json, T::class.java) as T
    }

    /**
     * 读取src/main/assets目录下json文件,转换成对应的List
     */
    inline fun <reified T> readJsonArrayFromAssets(context: Context, fileName: String): T {
        val inputStream = context.assets.open(fileName)
        val json = String(inputStream.readBytes())
        inputStream.close()
        return GsonHelper.fromJson(json, object : TypeToken<T>() {}.type) as T
    }

    /**
     * 读取src/main/res/raw目录下json文件,转换成对应的bean
     */
    inline fun <reified T> readJsonFromRaw(context: Context, @RawRes id: Int): T {
        val inputStream = context.resources.openRawResource(id)
        val json = String(inputStream.readBytes())
        inputStream.close()
        return GsonHelper.fromJson(json, T::class.java) as T
    }

    /**
     * 读取src/main/res/raw目录下json文件,转换成对应的List
     */
    inline fun <reified T> readJsonArrayFromRaw(context: Context, @RawRes id: Int): T {
        val inputStream = context.resources.openRawResource(id)
        val json = String(inputStream.readBytes())
        inputStream.close()
        return GsonHelper.fromJson(json, object : TypeToken<T>() {}.type) as T
    }

    /**
     * 复制src/main/assets目录下的文件到指定目录
     */
    fun copyAssets(context: Context, fileName: String, destDirectory: String): Boolean {
        var destDir = destDirectory
        var `in`: InputStream? = null
        var out: OutputStream? = null
        try {
            `in` = context.resources.assets.open(fileName)
            if (!destDir.endsWith(File.separator)) destDir += File.separator
            val parent = File(destDir)
            if (!parent.exists()) {
                parent.mkdirs()
            }
            out = FileOutputStream(destDir + fileName)
            val buffer = ByteArray(1024)
            var length: Int
            while (`in`.read(buffer).also { length = it } > 0) {
                out.write(buffer, 0, length)
            }
            out.flush()
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        } finally {
            try {
                `in`?.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            try {
                out?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return true
    }
}