package com.asterlsker.housepit.api.presentation.common.utils

import com.asterlsker.housepit.api.common.MEMBER_ID
import com.asterlsker.housepit.api.common.exception.ServiceException
import com.asterlsker.housepit.api.common.response.ResponseCode
import org.springframework.web.context.request.RequestAttributes
import org.springframework.web.context.request.RequestContextHolder.getRequestAttributes


object RequestContext {
    fun setAttribute(key: String, value: String) {
        if (getRequestAttributes() != null) {
            getRequestAttributes()?.setAttribute(key, value, RequestAttributes.SCOPE_REQUEST)
        }
    }

    fun getAttribute(key: String): String? {
        if (getRequestAttributes() != null) {
            return getRequestAttributes()?.getAttribute(key, RequestAttributes.SCOPE_REQUEST).toString()
        }
        return null
    }

    fun setMemberId(memberId: String) {
        setAttribute(MEMBER_ID, memberId)
    }

    fun getMemberId(): String {
        val memberId = getAttribute(MEMBER_ID)
        if (memberId.isNullOrBlank()) throw ServiceException(ResponseCode.NOT_FOUND_MEMBER_ID)
        return memberId
    }
}