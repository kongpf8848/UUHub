package io.github.kongpf8848.baselib.utils

import java.text.SimpleDateFormat
import java.util.*

object DateHelper {
    val DATE_FROMAT_DEFAULT =
        SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val DATE_FROMAT_YMD = SimpleDateFormat("yyyy-MM-dd")
    val DATE_FROMAT_HM = SimpleDateFormat("HH:mm")
    val DATE_FROMAT_YMD_HM =
        SimpleDateFormat("yyyy-MM-dd HH:mm")
    val DATE_FROMAT_YMD_T_HMS_SSS =
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
    const val DATA_PATTERN = "yyyy-MM-dd"
    val TIMEZONE_GMT = TimeZone.getTimeZone("GMT+0:00")
    val DATE_FORMAT_DMY=SimpleDateFormat("dd.MM.yyyy")

    fun formatDate(ms: Long?): String {
        val formatMillis = ms ?: 0L
        return formatDate(Date(timeToMs(formatMillis)))
    }

    @JvmOverloads
    fun formatDate(date: Date? = Date()): String {
        return formatDate(DATE_FROMAT_DEFAULT, date)
    }

    fun formatDate(format: String?, date: Date?): String {
        return formatDate(SimpleDateFormat(format), date)
    }

    fun formatDate(format: String?, ms: Long): String {
        return formatDate(SimpleDateFormat(format), ms)
    }

    //格式化日期
    fun formatDate(dateFormat: SimpleDateFormat, date: Date?): String {
        try {
            return dateFormat.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }

    fun formatDate(dateFormat: SimpleDateFormat, ms: Long): String {
        try {
            return dateFormat.format(ms)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }

    /**
     * 秒转换成"mm:ss
     * @param seconds
     * @return
     */
    fun secToTime(seconds: Long): String {
        val formatter = SimpleDateFormat("mm:ss")
        formatter.timeZone = TIMEZONE_GMT
        return formatter.format(Date(seconds * 1000))
    }

    /**
     * 毫秒转换成"mm:ss
     * @param seconds
     * @return
     */
    fun milToTime(seconds: Long): String {
        val formatter = SimpleDateFormat("mm:ss")
        formatter.timeZone = TIMEZONE_GMT
        return formatter.format(Date(seconds))
    }

    fun formatDate(millis: Long, pattern: String?): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = millis
        val simpleDateFormat =
            SimpleDateFormat(pattern, Locale.getDefault())
        return try {
            simpleDateFormat.format(calendar.time)
        } catch (e: Exception) {
            ""
        }
    }

    /**
     * 转为 13 位时间戳
     *
     * @param time
     * @return
     */
    fun timeToMs(time: Long): Long {
        return if (time > 1000000000000L) {
            time
        } else time * 1000
    }

    /**
     * 判断是否成年
     */
    fun checkAdult(birthDay: Calendar): Boolean {
        val current = Calendar.getInstance()
        val year = current[Calendar.YEAR] - birthDay[Calendar.YEAR]
        if (year > 18) {
            return true
        } else if (year < 18) {
            return false
        }
        // 如果年相等，就比较月份
        val month = current[Calendar.MONTH]+1 - birthDay[Calendar.MONTH]
        if (month > 0) {
            return true
        } else if (month < 0) {
            return false
        }
        // 如果月也相等，就比较天
        val day = current[Calendar.DAY_OF_MONTH] - birthDay[Calendar.DAY_OF_MONTH]
        return day >= 0
    }

    fun toDate(text:String,format:String):Date{
        val sdf=SimpleDateFormat(format);
        val date=sdf.parse(text);
        return date;
    }
}