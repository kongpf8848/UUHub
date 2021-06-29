package io.github.kongpf8848.common.base

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel

open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    protected var context: Context

    init {
        context = application.applicationContext
    }


    override fun onCleared() {
        super.onCleared()
    }
}