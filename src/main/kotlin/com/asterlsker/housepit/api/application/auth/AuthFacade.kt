package com.asterlsker.housepit.api.application.auth

import com.asterlsker.housepit.api.common.exception.ExternalServerException
import com.asterlsker.housepit.auth.domain.AuthService
import com.asterlsker.housepit.auth.domain.data.TokenData
import com.asterlsker.housepit.auth.domain.data.TokenProviderData
import com.asterlsker.housepit.core.enums.OAuth2Provider
import org.springframework.stereotype.Service

@Service
class AuthFacade(
    private val authService: AuthService
) {

    fun signIn(provider: OAuth2Provider, idToken: String): TokenData {
        try {
            val tokenProviderData = TokenProviderData(provider, idToken, null)
            return authService.signIn(tokenProviderData)
        } catch (e: Exception) {
            throw ExternalServerException(msg = e.message, errors = listOf("related module: auth"))
        }
    }

    fun link(accessToken: String, provider: OAuth2Provider, idToken: String) {
//        try {
            val tokenProviderData = TokenProviderData(provider, idToken, accessToken)
            authService.link(tokenProviderData)
//        } catch (e: Exception) {
//            throw ExternalServerException(msg = e.message, errors = listOf("related module: auth"))
//        }
    }

    fun signOut(accessToken: String) {
        try {
            authService.signOut(accessToken)
        } catch (e: Exception) {
            throw ExternalServerException(msg = e.message, errors = listOf("related module: auth"))
        }
    }

    fun refresh(refreshToken: String): TokenData {
        try {
            return authService.refresh(refreshToken)
        } catch (e: Exception) {
            throw ExternalServerException(msg = e.message, errors = listOf("related module: auth"))
        }
    }
}