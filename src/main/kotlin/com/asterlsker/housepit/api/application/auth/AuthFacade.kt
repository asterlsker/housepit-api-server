package com.asterlsker.housepit.api.application.auth

import com.asterlsker.housepit.auth.domain.AuthService
import com.asterlsker.housepit.auth.domain.data.TokenData
import com.asterlsker.housepit.auth.domain.data.TokenProviderData
import com.asterlsker.housepit.core.enums.OAuth2Provider
import org.springframework.stereotype.Service

@Service
class AuthFacade(
    private val authAction: AuthService
) {

    fun signIn(provider: OAuth2Provider, idToken: String): TokenData {
        val tokenProviderData = TokenProviderData(provider, idToken, null)
        return authAction.signIn(tokenProviderData)
    }
}