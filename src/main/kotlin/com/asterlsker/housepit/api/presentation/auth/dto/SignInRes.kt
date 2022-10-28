package com.asterlsker.housepit.api.presentation.auth.dto

import com.asterlsker.housepit.auth.domain.data.TokenData

data class SignInRes(
    val accessToken: String,
    val refreshToken: String
) {
    companion object {
        fun of(data: TokenData): SignInRes {
            return SignInRes(data.accessToken, data.refreshToken)
        }
    }
}
