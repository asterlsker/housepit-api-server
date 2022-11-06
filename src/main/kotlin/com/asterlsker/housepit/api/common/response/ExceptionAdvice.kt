package com.asterlsker.housepit.api.common.response

import com.asterlsker.housepit.api.common.exception.DomainException
import com.asterlsker.housepit.api.common.exception.ExternalServerException
import com.asterlsker.housepit.api.common.exception.ServiceException
import org.slf4j.LoggerFactory
import org.slf4j.event.Level
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionAdvice {
    private val log = LoggerFactory.getLogger(javaClass)


    @ExceptionHandler(ExternalServerException::class)
    fun externalServerException(e: ExternalServerException): CommonResponse<*> {
        printLog(e.code, e.stackTraceToString())
        return CommonResponse.fail(e.code, e.msg, e.errors)
    }

    @ExceptionHandler(DomainException::class)
    fun domainException(e: DomainException): CommonResponse<*> {
        printLog(e.code, e.stackTraceToString())
        return CommonResponse.fail(e.code, e.msg)
    }

    @ExceptionHandler(ServiceException::class)
    fun domainException(e: ServiceException): CommonResponse<*> {
        printLog(e.code, e.stackTraceToString())
        return CommonResponse.fail(e.code, e.msg, e.errors)
    }

    @ExceptionHandler(Exception::class)
    fun exception(e: Exception): CommonResponse<*> {
        log.error("Not Defined or Not Handled Exception occur", e)
        return CommonResponse.fail(ResponseCode.UNKNOWN)
    }

    private fun printLog(errorCode: ResponseCode, stackTrace: String) {
        val messageTemplate = "ErrorCode: ${errorCode.code}, Message: ${errorCode.message} StackTrace: $stackTrace"
        when (errorCode.logLevel) {
            Level.ERROR -> log.error(messageTemplate)
            Level.WARN -> log.warn(messageTemplate)
            Level.INFO -> log.info(messageTemplate)
            Level.DEBUG -> log.debug(messageTemplate)
            Level.TRACE -> log.trace(messageTemplate)
        }
    }
}