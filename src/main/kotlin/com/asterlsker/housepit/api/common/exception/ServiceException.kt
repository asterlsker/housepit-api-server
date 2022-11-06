package com.asterlsker.housepit.api.common.exception

import com.asterlsker.housepit.api.common.response.ResponseCode

class ServiceException(
    val code: ResponseCode = ResponseCode.INTERNAL_SERVER_ERROR,
    val msg: String? = null,
    val errors: List<Any>? = null
) : RuntimeException(msg ?: code.message)