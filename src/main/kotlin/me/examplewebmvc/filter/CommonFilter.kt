package me.examplewebmvc.filter

import org.springframework.beans.factory.annotation.Value
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
    @Value("\${api.base.path:/api}")
    private val apiBasePath: String = ""

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val httpServletRequest = request as HttpServletRequest
        if(includeUrlPattern(httpServletRequest)){
            logger.info("Reqeust method : ${httpServletRequest.method}, url : ${httpServletRequest.requestURL}")
        }
        chain?.doFilter(request, response)
    }

    private fun includeUrlPattern(httpServletRequest: HttpServletRequest): Boolean{
        val uri = httpServletRequest.requestURI
        if(uri.startsWith(apiBasePath)){
            return true
        }
        return false
    }
}