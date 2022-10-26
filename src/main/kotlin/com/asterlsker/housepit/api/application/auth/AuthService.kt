package com.asterlsker.housepit.api.application.auth

import com.asterlsker.housepit.auth.domain.AuthAction
import com.asterlsker.housepit.auth.domain.dto.ProviderTokenDto
import com.asterlsker.housepit.auth.domain.dto.ServiceTokenDto
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val authAction: AuthAction
) {

    fun signIn(idToken: String): ServiceTokenDto {
        return authAction.signIn(ProviderTokenDto(idToken))
    }
}