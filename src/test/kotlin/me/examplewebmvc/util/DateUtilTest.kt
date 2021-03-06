package me.examplewebmvc.util

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import java.sql.Timestamp

internal class DateUtilTest{
    @Test
    fun dateUtilTest(){
        val now = DateUtil.getNowMilliSecond()
        println("Now MilliSc : $now")

        val dateString = DateUtil.getDateString(Timestamp(now))
        println(dateString)

        val mill = DateUtil.getTimestamp(dateString)
        println(mill.time)

        assertNotNull(dateString)
    }
}