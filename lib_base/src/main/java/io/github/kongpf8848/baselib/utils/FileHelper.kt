package io.github.kongpf8848.baselib.utils

import android.content.Context
import android.text.TextUtils
import java.io.*

object FileHelper {
    /**
     * 复制文件
     * @param source
     * @param target
     */
    fun copyFile(source: File?, target: File?) {
        try {
            FileInputStream(source).use { fis ->
                FileOutputStream(target).use { fos ->
                    val buffer = ByteArray(1024)
                    var len: Int
                    while (fis.read(buffer).also { len = it } > 0) {
                        fos.write(buffer, 0, len)
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 删除指定目录
     * @param dir
     * @return
     */
    fun deleteDir(dir: File): Boolean {
        if (dir.isDirectory) {
            val children = dir.list()
            for (i in children.indices) {
                val success =
                    deleteDir(File(dir, children[i]))
                if (!success) {
                    return false
                }
            }
        }
        return dir.delete()
    }

    /**
     * 获取Assets文件夹下的Json
     * @param context
     * @param fileName
     * @return
     */
    fun getJson(context: Context, fileName: String?): String {
        //将json数据变成字符串
        val stringBuilder = StringBuilder()
        try {
            //获取assets资源管理器
            val assetManager = context.assets
            //通过管理器打开文件并读取
            val bf = BufferedReader(
                InputStreamReader(
                    assetManager.open(fileName!!)
                )
            )
            var line: String?
            while (bf.readLine().also { line = it } != null) {
                stringBuilder.append(line)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return stringBuilder.toString()
    }

    /**
     * 根据路径获取文件名称
     * @param path
     * @return
     */
    fun getFileName(path: String): String? {
        if (!TextUtils.isEmpty(path)) {
            val index = path.lastIndexOf("/")
            if (index > -1) {
                return path.substring(index + 1)
            }
        }
        return null
    }

    fun assetFile(context: Context, assetsFilePath: String) =
        File(StorageHelper.getInternalCachePath(context), assetsFilePath)

    fun copyAssets(
        context: Context,
        assetsFile: String,
        cacheFile: String
    ): File? {
        val outFile = File(StorageHelper.getInternalCachePath(context), cacheFile)
        if (!outFile.parentFile.exists()) {
            outFile.parentFile.mkdirs()
        }
        try {
            context.assets.open(assetsFile).use { inputStream ->
                FileOutputStream(outFile).use { fos ->
                    val buffer = ByteArray(1024)
                    var byteCount: Int
                    while (inputStream.read(buffer).also { byteCount = it } != -1) {
                        fos.write(buffer, 0, byteCount)
                    }
                    fos.flush()
                    return outFile
                }

            }
        } catch (e: java.lang.Exception) {
            return null
        }
    }

}