package com.asterlsker.housepit.api.common.response

import org.slf4j.event.Level
import org.springframework.http.HttpStatus

enum class ResponseCode(
    val status: HttpStatus,
    val code: String,
    val message: String,
    val logLevel: Level = Level.INFO
) {
    // Common Responses
    SUCCESS(HttpStatus.OK, "COM0001", "Success"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "COM0002", "Bad Request", Level.ERROR),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COM0003", "Internal Server Error", Level.ERROR),
    NOT_FOUND(HttpStatus.NOT_FOUND, "COM0004", "NOT_FOUND", Level.ERROR),

    // Custom Responses

}