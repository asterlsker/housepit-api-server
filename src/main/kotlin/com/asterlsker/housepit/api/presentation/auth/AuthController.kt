package com.asterlsker.housepit.api.presentation.auth

import com.asterlsker.housepit.api.application.auth.AuthFacade
import com.asterlsker.housepit.api.common.AUTH_LINK
import com.asterlsker.housepit.api.common.AUTH_REFRESH
import com.asterlsker.housepit.api.common.AUTH_SIGNIN
import com.asterlsker.housepit.api.common.AUTH_SIGNOUT
import com.asterlsker.housepit.api.common.response.CommonResponse
import com.asterlsker.housepit.api.presentation.auth.dto.LinkReq
import com.asterlsker.housepit.api.presentation.auth.dto.RefreshRes
import com.asterlsker.housepit.api.presentation.auth.dto.SignInReq
import com.asterlsker.housepit.api.presentation.auth.dto.SignInRes
import com.asterlsker.housepit.api.presentation.common.utils.TokenHandler
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
    fun signIn(@Valid @RequestBody request: SignInReq): CommonResponse<SignInRes> {
        val payload = SignInRes.of(authFacade.signIn(request.provider, request.idToken))
        return CommonResponse.success(payload)
    }

    @PostMapping(AUTH_LINK)
    fun link(
        @RequestHeader("Authorization") accessToken: String,
        @RequestBody @Valid request: LinkReq
    ): CommonResponse<Unit> {
        val token = TokenHandler.getToken(accessToken);
        authFacade.link(token, request.provider, request.idToken)
        return CommonResponse.success()
    }

    @PostMapping(AUTH_SIGNOUT)
    fun signOut(@RequestHeader("Authorization") accessToken: String): CommonResponse<Unit> {
        val token = TokenHandler.getToken(accessToken);
        authFacade.signOut(token)
        return CommonResponse.success()
    }

    @PostMapping(AUTH_REFRESH)
    fun refresh(@RequestHeader("Authorization") refreshToken: String): CommonResponse<RefreshRes> {
        val token = TokenHandler.getToken(refreshToken);
        val payload = RefreshRes.of(authFacade.refresh(token))
        return CommonResponse.success(payload = payload)
    }
}