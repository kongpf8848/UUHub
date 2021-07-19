package io.github.kongpf8848.baselib.utils

import io.github.kongpf8848.baselib.utils.ByteHelper.byteArray2HexString
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

object AlgorithmHelper {

    private val mapList: MutableMap<Int, String> = HashMap()

    const val SHA1 = 0
    const val SHA256 = 1
    const val SHA384 = 2
    const val SHA512 = 3
    const val MD5 = 4


    init {
        mapList[SHA1] = "SHA-1"
        mapList[SHA256] = "SHA-256"
        mapList[SHA384] = "SHA-384"
        mapList[SHA512] = "SHA-512"
        mapList[MD5] = "MD5"
    }

    /**
     * 获取字符串的Hash值
     * @param text
     * @param algorithm
     * @return
     */
    fun getHash(text: String, algorithm: Int): String? {
        var hash: String? = null
        try {
            if (mapList.containsKey(algorithm)) {
                val digest =
                    MessageDigest.getInstance(mapList[algorithm])
                val bytes =
                    text.toByteArray(StandardCharsets.UTF_8)
                digest.update(bytes, 0, bytes.size)
                hash = byteArray2HexString(digest.digest())
            }
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return hash
    }

    /**
     * 获取字符串的MD5值
     * @param text
     * @return
     */
    fun getMD5(text: String): String? {
        return getHash(text, MD5)
    }
}
