package me.examplewebmvc.util

import java.util.*

open class CommonUtil {
    companion object {
        /**
         * Get Random String by Length
         *
         * @param length Int
         * @return String
         */
        fun getRandomString(length: Int): String {
            val temp = StringBuffer()
            val rnd = Random()
            for (i in 0 until length) {
                when (rnd.nextInt(3)) {
                    0 ->                     // a-z
                        temp.append((rnd.nextInt(26) + 97).toChar())
                    1 ->                     // A-Z
                        temp.append((rnd.nextInt(26) + 65).toChar())
                    2 ->                     // 0-9
                        temp.append(rnd.nextInt(10))
                }
            }
            return temp.toString()
        }
    }
}