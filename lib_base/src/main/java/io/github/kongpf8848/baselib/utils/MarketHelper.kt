package io.github.kongpf8848.baselib.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.TextUtils

object MarketHelper {

    const val MARKET_DATA = "market://details"
    const val GOOGLEPLAY_PACKAGENAME = "com.android.vending"


    //跳转到GooglePlay商店
    fun gotoGooglePlay(
        context: Context,
        appPackageName: String?
    ) {
        gotoMarket(context, appPackageName, GOOGLEPLAY_PACKAGENAME)
    }

    //跳转到应用市场
    fun gotoMarket(
        context: Context,
        appPackageName: String?,
        marketPackageName: String? = null
    ) {
        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(
                String.format(
                    "%s?id=%s",
                    MARKET_DATA,
                    appPackageName
                )
            )
            if (!TextUtils.isEmpty(marketPackageName)) {
                intent.setPackage(marketPackageName)
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}