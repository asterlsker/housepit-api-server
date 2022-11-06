package com.asterlsker.housepit.api.common.exception

import com.asterlsker.housepit.api.common.response.ResponseCode

class DomainException(
    val code: ResponseCode = ResponseCode.DOMAIN,
    val msg: String?
) : RuntimeException(msg ?: code.message)
