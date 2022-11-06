package com.asterlsker.housepit.api.common.response

import com.fasterxml.jackson.annotation.JsonInclude

data class ServiceResponse<T>(
    val code: String,
    val message: String?,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val payload: T?,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val errors: List<Any>?
) {
    companion object {
        fun <T> of(response: CommonResponse<T>): ServiceResponse<T> {
            return ServiceResponse(
                code = response.code,
                message = response.message,
                payload = response.payload,
                errors = response.errors
            )
        }
    }
}
