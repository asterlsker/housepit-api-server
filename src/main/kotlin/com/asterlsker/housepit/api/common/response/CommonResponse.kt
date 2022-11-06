package com.asterlsker.housepit.api.common.response

import org.springframework.http.HttpStatus
import org.springframework.validation.Errors

data class CommonResponse<T>(
    val code: String,
    val message: String?,
    val payload: T?,
    val errors: List<Any>? = null,
    val httpStatus: HttpStatus
) {
    companion object {

        fun success(): CommonResponse<Unit> {
            return CommonResponse(
                code = ResponseCode.SUCCESS.code,
                message = ResponseCode.SUCCESS.message,
                payload = null,
                httpStatus = ResponseCode.SUCCESS.status
            )
        }

        fun <T> success(payload: T?): CommonResponse<T> {
            return CommonResponse(
                code = ResponseCode.SUCCESS.code,
                message = ResponseCode.SUCCESS.message,
                payload = payload,
                httpStatus = ResponseCode.SUCCESS.status
            )
        }

        fun <T> success(message: String?, payload: T?, httpStatus: HttpStatus?): CommonResponse<T> {
            return CommonResponse(
                code = ResponseCode.SUCCESS.code,
                message = message ?: ResponseCode.SUCCESS.message,
                payload = payload,
                httpStatus = httpStatus ?: ResponseCode.SUCCESS.status
            )
        }

        fun fail(errorCode: ResponseCode): CommonResponse<*> {
            return CommonResponse(
                code = errorCode.code,
                message = null,
                payload = null,
                httpStatus = errorCode.status
            )
        }

        fun fail(errorCode: ResponseCode, errors: Errors?): CommonResponse<*> {
            return CommonResponse(
                code = errorCode.code,
                message = errorCode.message,
                payload = null,
                errors = ErrorField.of(errors),
                httpStatus = errorCode.status
            )
        }

        fun fail(errorCode: ResponseCode, message: String?): CommonResponse<*> {
            return CommonResponse(
                code = errorCode.code,
                message = message ?: errorCode.message,
                payload = null,
                httpStatus = errorCode.status
            )
        }

        fun fail(errorCode: ResponseCode, message: String?, errors: List<Any>?): CommonResponse<*> {
            return CommonResponse(
                code = errorCode.code,
                message = message ?: errorCode.message,
                payload = null,
                errors = errors,
                httpStatus = errorCode.status
            )
        }
    }
}
