package me.examplewebmvc.util

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import java.sql.Timestamp
import java.text.SimpleDateFormat

open class DateUtil {
    companion object{
        private val logger: Log = LogFactory.getLog(DateUtil::class.java)
        private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

        /**
         * Get Timestamp from string ('YYYY-mm-dd HH:mi:ss')
         *
         * @param dateStr String
         * @return timestamp Timestamp
         */
        fun getTimestamp(dateStr: String): Timestamp{
            try{
                val date = dateFormat.parse(dateStr)
                return Timestamp(date.time)
            } catch(e: Exception){
                logger.error(e.localizedMessage)
                throw e
            }
        }

        /**
         * Get Date String from timestamp ('YYYY-mm-dd HH:mi:ss')
         *
         * @param date Timestamp
         * @return dateStr String ('YYYY-mm-dd HH:mi:ss')
         */
        fun getDateString(date: Timestamp): String{
            try {
                return dateFormat.format(date.time)
            } catch (e: Exception) {
                logger.error(e.localizedMessage)
                throw e
            }
        }

        /**
         * Get Now timestamp
         *
         * @return timestamp
         */
        fun getNow(): Timestamp{
            return Timestamp(getNowMilliSecond())
        }

        /**
         * Get Now time(millisecond)
         *
         * @return time Long
         */
        fun getNowMilliSecond(): Long{
            return System.currentTimeMillis()
        }

        /**
         * Get Now Date String
         *
         * @return dateString String
         */
        fun getNowDateStr(): String{
            return dateFormat.format(System.currentTimeMillis())
        }
    }
}