package com.asterlsker.housepit.api.presentation.auth.dto

import com.asterlsker.housepit.auth.domain.dto.ServiceTokenDto

data class SignInRes(
    val accessToken: String,
    val refreshToken: String
) {
    companion object {
        fun of(response: ServiceTokenDto): SignInRes {
            return SignInRes(response.accessToken!!, response.refreshToken!!)
        }
    }
}
