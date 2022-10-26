package com.asterlsker.housepit.api.presentation.auth

import com.asterlsker.housepit.api.application.auth.AuthService
import com.asterlsker.housepit.api.common.AUTH_SIGNIN
import com.asterlsker.housepit.api.presentation.auth.dto.SignInRes
import com.asterlsker.housepit.auth.domain.dto.ServiceTokenDto
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    private val authService: AuthService
) {
    @PostMapping(AUTH_SIGNIN)
    fun signIn(@RequestHeader("Authorization") idToken: String) {
        val serviceToken: ServiceTokenDto = authService.signIn(idToken)
        SignInRes.of(serviceToken)
    }

    @PostMapping
    fun refresh(@RequestHeader("Authorization") refreshToken: String) {

    }


}