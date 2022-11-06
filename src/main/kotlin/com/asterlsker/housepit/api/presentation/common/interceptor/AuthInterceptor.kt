package com.asterlsker.housepit.api.presentation.common.interceptor

import com.asterlsker.housepit.api.presentation.common.utils.RequestContext
import com.asterlsker.housepit.api.presentation.common.utils.TokenHandler
import com.asterlsker.housepit.auth.domain.AuthService
import com.asterlsker.housepit.core.annotation.Authentication
import com.asterlsker.housepit.core.enums.AuthenticationScope
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthInterceptor(
    private val authService: AuthService
) : HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val handlerMethod: HandlerMethod = handler as HandlerMethod
        val authentication: Authentication? = handlerMethod.getMethodAnnotation(Authentication::class.java)

        if (authentication != null && checkAllowCase(authentication, request)) {
            return true
        }

        try {
            val accessToken = TokenHandler.getToken(request)
            val member = authService.decode(accessToken)
            RequestContext.setMemberId(member.memberId)
            return true
        } catch (e: Exception) {
            return false
        }
    }

    private fun checkAllowCase(authentication: Authentication, request: HttpServletRequest): Boolean {
        if (authentication.scope == AuthenticationScope.NONE) return true
        if (authentication.scope == AuthenticationScope.OPTIONAL && request.getHeader("Authorization").isBlank()) {
            return true
        }
        return false
    }
}