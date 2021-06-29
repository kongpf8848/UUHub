package io.github.kongpf8848.baselib.utils

object ByteHelper {
    private const val HEXSTRING = "0123456789ABCDEF"

    fun hexChar2Byte(c: Char): Byte {
        return HEXSTRING.indexOf(c.toString().toUpperCase()).toByte()
    }

    //字节数组转化为16进制字符串
    fun byteArray2HexString(bArr: ByteArray): String {
        val builder = StringBuilder()
        var i = 0
        val len = bArr.size
        while (i < len) {
            builder.append(String.format("%02x", bArr[i]))
            i++
        }
        return builder.toString()
    }

}