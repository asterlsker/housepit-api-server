package com.asterlsker.housepit.api.presentation.auth

import com.asterlsker.housepit.api.application.auth.AuthFacade
import com.asterlsker.housepit.api.common.AUTH_SIGNIN
import com.asterlsker.housepit.api.presentation.auth.dto.SignInReq
import com.asterlsker.housepit.api.presentation.auth.dto.SignInRes
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class AuthController(
    private val authFacade: AuthFacade
) {
    @PostMapping(AUTH_SIGNIN)
    fun signIn(
        @RequestHeader("Authorization") idToken: String,
        @RequestBody @Valid request: SignInReq
    ): SignInRes {
        val data = authFacade.signIn(request.provider, idToken)
        return SignInRes.of(data)
    }

    @PostMapping
    fun refresh(@RequestHeader("Authorization") refreshToken: String) {

    }


}