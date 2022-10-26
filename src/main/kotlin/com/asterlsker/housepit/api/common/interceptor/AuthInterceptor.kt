package com.asterlsker.housepit.api.common.interceptor

import com.asterlsker.housepit.core.annotation.Authentication
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthInterceptor : HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val handlerMethod: HandlerMethod = handler as HandlerMethod
        val authentication: Authentication? = handlerMethod.getMethodAnnotation(Authentication::class.java)

        println(authentication?.scope)
        return true
    }
}