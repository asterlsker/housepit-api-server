package com.asterlsker.housepit.api.presentation.common.interceptor

import com.asterlsker.housepit.api.common.SUPER_ACCESS_TOKEN
import com.asterlsker.housepit.api.common.SUPER_MEMBER_ID
import com.asterlsker.housepit.api.common.exception.ServiceException
import com.asterlsker.housepit.api.common.response.ResponseCode.INVALID_TOKEN
import com.asterlsker.housepit.api.common.utils.ProfileHandler
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
    private val authService: AuthService,
    private val profileHandler: ProfileHandler
) : HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (handler !is HandlerMethod) return false
        val authentication: Authentication? = handler.getMethodAnnotation(Authentication::class.java)

        if (authentication != null && checkAllowCase(authentication, request)) return true
        if (handleAuthorization(request)) return true

        throw ServiceException(INVALID_TOKEN)
    }

    /**
     * true: token 이 필요 없는 경우
     * false: token 이 필요한 경우
     */
    private fun checkAllowCase(authentication: Authentication, request: HttpServletRequest): Boolean {
        if (authentication.scope == AuthenticationScope.NONE) return true
        if (authentication.scope == AuthenticationScope.OPTIONAL && request.getHeader("Authorization").isBlank()) {
            return true
        }
        return false
    }

    /**
     * true: Authorization 값이 정상인 경우
     * false: Authorization 값이 정상이 아닌 경우
     */
    private fun handleAuthorization(request: HttpServletRequest): Boolean {
        try {
            val accessToken = TokenHandler.getToken(request)
            if (profileHandler.acceptable("local", "dev") && accessToken == SUPER_ACCESS_TOKEN) {
                RequestContext.setMemberId(SUPER_MEMBER_ID)
                return true
            }
            val member = authService.decode(accessToken)
            RequestContext.setMemberId(member.memberId)
            return true
        } catch (e: Exception) {
            return false
        }
    }
}