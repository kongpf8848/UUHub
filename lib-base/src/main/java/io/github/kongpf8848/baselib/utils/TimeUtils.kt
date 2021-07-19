package io.github.kongpf8848.baselib.utils

import java.text.SimpleDateFormat
import java.util.*

object TimeUtils {
    const val PATTERN_Y_M_D_H_M_S = "yyyy-MM-dd HH:mm:ss"
    const val PATTERN_Y_M_D = "yyyy-MM-dd"

    /**
     * 将秒转换成hh:mm:ss
     *
     * @param seconds
     * @return
     */
    fun secondsToHMS(seconds: Int): String {
        var hour = 0
        var min = 0
        var second = 0
        if (seconds >= 60) {
            min = seconds / 60
            second = seconds % 60
            if (min >= 60) {
                hour = min / 60
                min = min % 60
            }
        } else {
            second = seconds
        }
        return String.format(
            Locale.getDefault(),
            "%02d:%02d:%02d",
            hour,
            min,
            second
        )
    }

    /**
     * 返回当前手机时区对应的时间
     *
     * @param second
     * @return
     */
    fun second2Date(second: Long): String {
        return millisecond2Date(second * 1000)
    }

    /**
     * 返回当前手机时区对应的时间
     *
     * @param second
     * @return
     */
    fun second2YeadMonthDay(second: Long): String {
        return millisecond2YeadMonthDay(second * 1000)
    }

    /**
     * 返回当前手机时区对应的时间
     *
     * @param millisecond
     * @return
     */
    fun millisecond2Date(millisecond: Long): String {
        return SimpleDateFormat(PATTERN_Y_M_D_H_M_S)
            .format(Date(millisecond))
    }

    /**
     * 返回当前手机时区对应的时间
     *
     * @param millisecond
     * @return
     */
    fun millisecond2YeadMonthDay(millisecond: Long): String {
        return SimpleDateFormat(PATTERN_Y_M_D)
            .format(Date(millisecond))
    }

    /**
     * 返回  0 时区时间
     *
     * @param second
     * @return
     */
    fun second2GMTDate(second: Long): String {
        return millisecond2GMTDate(second * 1000)
    }

    /**
     * 返回  0 时区时间
     *
     * @param millisecond
     * @return
     */
    fun millisecond2GMTDate(millisecond: Long): String {
        val simpleDateFormat =
            SimpleDateFormat(PATTERN_Y_M_D_H_M_S)
        simpleDateFormat.timeZone = TimeZone.getTimeZone("GMT")
        return simpleDateFormat.format(Date(millisecond))
    }
}