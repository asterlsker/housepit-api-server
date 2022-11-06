package com.asterlsker.housepit.api.presentation.common.utils

import com.asterlsker.housepit.api.common.exception.ServiceException
import com.asterlsker.housepit.api.common.response.ResponseCode
import javax.servlet.http.HttpServletRequest

object TokenHandler {
    fun getToken(request: HttpServletRequest): String {
        val authentication = request.getHeader("Authorization")
        return getTokenCore(authentication)
    }

    fun getToken(request: String): String {
        return getTokenCore(request)
    }

    private fun getTokenCore(request: String): String {
        if (request.isBlank()) throw ServiceException(ResponseCode.TOKEN_REQUIRED)
        val tokens = request.split(" ")
        if (tokens.size != 2) throw ServiceException(ResponseCode.INVALID_TOKEN)
        return tokens[1]
    }
}