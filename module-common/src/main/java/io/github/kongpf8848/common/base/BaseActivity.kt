package io.github.kongpf8848.common.base

import android.content.Intent
import android.os.Bundle
import io.github.kongpf8848.baselib.swipeback.SwipBacActivity
import io.github.kongpf8848.baselib.utils.LogUtils

open class BaseActivity : SwipBacActivity(){

    val TAG=javaClass.simpleName
    protected lateinit var baseActivity:BaseActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.d(TAG, "onCreate called")
        baseActivity=this
    }

    override fun onStart() {
        super.onStart()
        LogUtils.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        LogUtils.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        LogUtils.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        LogUtils.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtils.d(TAG, "onDestroy() called")
    }

    override fun onRestart() {
        super.onRestart()
        LogUtils.d(TAG, "onRestart() called")
    }

    override fun onContentChanged() {
        super.onContentChanged()
        LogUtils.d(TAG, "onContentChanged() called")
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        LogUtils.d(TAG, "onPostCreate() called")
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        LogUtils.d(TAG, "onNewIntent() called")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        LogUtils.d(TAG, "onSaveInstanceState() called")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        LogUtils.d(TAG, "onRestoreInstanceState() called")
    }

}