package io.github.kongpf8848.baselib.utils

import android.app.Activity
import android.app.KeyguardManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings


object IntentUtils {

    const val MARKET_DETAIL_SCHEME = "market://details?id="
    const val MARKET_DETAIL_WEB_URL = "https://play.google.com/store/apps/details?id="
    const val GOOGLEPLAY_PACKAGENAME = "com.android.vending"
    const val AUTHENTICATOR_PACKAGENAME = "com.google.android.apps.authenticator2"

    /**
     * 已安装apk ？
     */
    fun isInstalledApk(context: Context, pkg: String): Boolean = try {
        context.packageManager.getPackageInfo(pkg, PackageManager.GET_ACTIVITIES)
        true
    } catch (e: PackageManager.NameNotFoundException) {
        false
    }

    /**
     * 启动指定应用市场下载指定apk
     */
    fun launcherMarket(
        context: Context,
        marketPkg: String?,
        appPkg: String
    ): Boolean = try {
        context.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("$MARKET_DETAIL_SCHEME$appPkg")
            ).apply {
                marketPkg?.run { this@apply.setPackage(this) }
                flags = flags and Intent.FLAG_ACTIVITY_NEW_TASK
            })
        true
    } catch (e: Exception) {
        false
    }

    /**
     * 启动web版本GooglePlay
     */
    fun launcherWebGoogleMarket(context: Context, appPkg: String) {
        context.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("$MARKET_DETAIL_WEB_URL$appPkg")
            ).apply {
                flags = flags and Intent.FLAG_ACTIVITY_NEW_TASK
            }
        )
    }

    /**
     * 打开系统安全设置
     */
    fun launcherSystemSecuritySettings(context: Context) {
        context.startActivity(
            Intent(Settings.ACTION_SECURITY_SETTINGS).apply {
                flags = flags and Intent.FLAG_ACTIVITY_NEW_TASK
            }
        )
    }

    fun launcherSystemSecurtyAuthentication(
        activity: Activity,
        title: CharSequence,
        desc: CharSequence,
        requestCode: Int
    ) {
        (activity.getSystemService(Context.KEYGUARD_SERVICE) as? KeyguardManager)?.createConfirmDeviceCredentialIntent(
            title,
            desc
        )?.run {
            activity.startActivityForResult(
                this,
                requestCode
            )
        }
    }


}