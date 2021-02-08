package me.examplewebmvc.aop

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.declaredMemberProperties

@Aspect
@Component
class AuthorAop {
    val logger: Log = LogFactory.getLog(AuthorAop::class.java)
    val jsonObjectMapper = jacksonObjectMapper()

    @Before("@annotation(me.examplewebmvc.annotation.AuthorDefaultSet)")
    fun setAuthorDefault(jointPoint: JoinPoint) {
        val request =
            (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).request // request 정보

        logger.info("Request URI: " + request.requestURI)
        logger.info("Request IP: " + request.remoteAddr)
        logger.info("Request Method: " + request.method)

        jointPoint.args.forEach {
            val requestBodyObject = jsonObjectMapper.convertValue(it, it.javaClass) // get request body object

            // kotlin reflection
            requestBodyObject.javaClass.kotlin.declaredMemberProperties.forEach { prop ->
                with(prop) {
                    if (name == "author" && get(requestBodyObject) == null) {
                        logger.info("Set default value in RequestBody!!")
                        (prop as KMutableProperty<*>).setter.call(it, "unknown")
                    }
                }
            }
        }
    }
}




