package io.github.kongpf8848.baselib.utils

import android.app.Activity


object ActivityContainer {

    private val acts: MutableList<Activity> = mutableListOf()

    fun add(act: Activity) {
        acts.add(act)
    }

    fun remove(act: Activity) {
        acts.remove(act)
    }

    fun finishAllBeside(className: String? = null) {
        (acts.size - 1 downTo 0).forEach {
            if (acts[it].javaClass.name != className) {
                acts[it].finish()
                acts[it].overridePendingTransition(0, 0)
            }
        }
    }

    /**
     * 获取倒数第二个Activity
     */
    fun getPenultimateActivity(): Activity? {
        var activity: Activity? = null
        try {
            if (acts.size > 1) {
                activity = acts[acts.size - 2]
            }
        } catch (ignored: Exception) {
        }
        return activity
    }

}