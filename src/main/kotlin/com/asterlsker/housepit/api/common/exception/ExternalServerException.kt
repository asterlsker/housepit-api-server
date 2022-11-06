package com.asterlsker.housepit.api.common.exception

import com.asterlsker.housepit.api.common.response.ResponseCode
import org.springframework.validation.Errors

class ExternalServerException(
    val code: ResponseCode = ResponseCode.EXTERNAL_SERVER_ERROR,
    val msg: String?,
    val errors: List<Any>? = null
) : RuntimeException(msg ?: code.message)
