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
    EXTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COM0004", "External Server Error", Level.ERROR),
    NOT_FOUND(HttpStatus.NOT_FOUND, "COM0005", "Not Found Error", Level.ERROR),
    UNKNOWN(HttpStatus.INTERNAL_SERVER_ERROR, "COM0006", "Unknown Error", Level.ERROR),

    // Custom Responses
    TOKEN_REQUIRED(HttpStatus.BAD_REQUEST, "CUS0001", "Token Required"),
    INVALID_TOKEN(HttpStatus.BAD_REQUEST, "CUS0002", "Invalid Token"),
    NOT_FOUND_MEMBER_ID(HttpStatus.BAD_REQUEST, "CUS0003", "Not Found MemberID"),

    // Hierarchy
    DOMAIN(HttpStatus.BAD_REQUEST, "HIE0001", "Domain Layer Error"),
    CONVERT(HttpStatus.BAD_REQUEST, "HIE0002", "Convert Error"),
    VALIDATION(HttpStatus.BAD_REQUEST, "HIE0003", "Validation Error"),
    ENTITY(HttpStatus.BAD_REQUEST, "HIE0004", "Entity Error"),
}