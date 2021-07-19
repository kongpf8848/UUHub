package io.github.kongpf8848.baselib.utils

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Process
import androidx.core.content.FileProvider
import java.io.File

object ApkHelper {

    //判断程序是否为Debug模式
    fun isDebugMode(context: Context): Boolean {
        try {
            val packageName = context.packageName
            val pkginfo = context.packageManager
                .getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
            if (pkginfo != null) {
                val info = pkginfo.applicationInfo
                return info.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0
            }
        } catch (e: Exception) {
        }
        return false
    }

    //获取AndroidManifest.xml中的meta标签数据
    fun getApplicationMetaValue(
        context: Context,
        name: String?
    ): String {
        var value = ""
        try {
            val appInfo = context.packageManager
                .getApplicationInfo(context.packageName, PackageManager.GET_META_DATA)
            value = appInfo.metaData[name].toString()
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return value
    }

    fun getAppVersionName(context: Context): String {
        return getAppVersionName(context, context.packageName)
    }

    fun getAppVersionCode(context: Context): Int {
        return getAppVersionCode(context, context.packageName)
    }

    //获取指定包名的版本名称
    fun getAppVersionName(
        context: Context,
        packageName: String?
    ): String {
        var version = ""
        try {
            version = context.packageManager.getPackageInfo(packageName!!, 0).versionName
        } catch (e: PackageManager.NameNotFoundException) {
        }
        return version
    }

    //获取指定包名的版本号
    fun getAppVersionCode(context: Context, packageName: String?): Int {
        var versionCode = 0
        try {
            versionCode = context.packageManager.getPackageInfo(packageName!!, 0).versionCode
        } catch (e: PackageManager.NameNotFoundException) {
        }
        return versionCode
    }

    /**
     * 安装Apk
     * @param context
     * @param file
     * @param authority
     */
    fun installApk(
        context: Context,
        file: File?,
        authority: String?
    ) {
        if (file == null || !file.exists()) {
            return
        }
        try {
            /**
             * 跳转到设置页面后,无法得到结果回调，暂时mark
             */
//            PackageManager pm= context.getPackageManager();
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                if (!pm.canRequestPackageInstalls()) {
//                    Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, Uri.parse("package:"+ context.getPackageName()));
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    context.startActivity(intent);
//                    return;
//                }
//            }
            val intent = Intent(Intent.ACTION_VIEW)
            val uri: Uri
            uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                FileProvider.getUriForFile(context, authority!!, file)
            } else {
                Uri.fromFile(file)
            }
            intent.setDataAndType(uri, "application/vnd.android.package-archive")
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    //判断当前进程是否为主进程
    fun isMainProcess(context: Context): Boolean {
        val am =
            context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val processInfos =
            am.runningAppProcesses
        val mainProcessName = context.packageName
        val myPid = Process.myPid()
        for (info in processInfos) {
            if (info.pid == myPid && mainProcessName == info.processName) {
                return true
            }
        }
        return false
    }

    //判断指定应用是否安装
    fun isInstalled(context: Context, packageName: String?): Boolean {
        val pm = context.packageManager
        return try {
            val info = pm.getPackageInfo(packageName!!, 0)
            info != null
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            false
        }
    }

    //判断当前应用是否从Google Play下载
    fun isInstalledFromGooglePlay(context: Context): Boolean {
        try {
            val installer =
                context.packageManager.getInstallerPackageName(context.packageName)
            return installer != null && installer == MarketHelper.GOOGLEPLAY_PACKAGENAME
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }
}