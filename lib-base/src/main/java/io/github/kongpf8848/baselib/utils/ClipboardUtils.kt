package io.github.kongpf8848.baselib.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

object ClipboardUtils {

    fun copyText(context: Context, text: CharSequence, label: CharSequence = "") {
        val clipboardManager =
            context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val copyText = ClipData.newPlainText(label, text)
        clipboardManager.setPrimaryClip(copyText)
    }
}