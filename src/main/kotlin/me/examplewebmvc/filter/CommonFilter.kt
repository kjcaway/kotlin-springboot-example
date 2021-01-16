package me.examplewebmvc.filter

import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

@Component
@Order(1)
class CommonFilter: GenericFilterBean() {
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val httpServletRequest = request as HttpServletRequest
        logger.info("Reqeust method : ${httpServletRequest.method}, url : ${httpServletRequest.requestURL}")
        try {
            chain?.doFilter(request, response)
            logger.info("Response content-type : ${response?.contentType}")
        } catch (e: Exception){
            logger.error(e.printStackTrace())
        }
    }
}