package com.asterlsker.housepit.api.common.response

import org.springframework.validation.Errors
import java.util.*
import java.util.stream.Collectors

class ErrorField {
    var field: String? = null
    var value: String? = null
    var reason: String? = null

    companion object {
        fun of(errors: Errors?): List<ErrorField> {
            if(errors == null) return emptyList()
            return errors.fieldErrors.stream()
                .map { fieldError ->
                    ErrorField().apply {
                        field = fieldError.field
                        value = fieldError.rejectedValue?.let { getOrEmpty(it) }
                        reason = fieldError.defaultMessage?.let { getOrEmpty(it) }
                    }
                }
                .collect(Collectors.toList())
        }

        private fun getOrEmpty(target: Any): String {
            if (Objects.isNull(target)) return ""
            return target.toString()
        }
    }
}