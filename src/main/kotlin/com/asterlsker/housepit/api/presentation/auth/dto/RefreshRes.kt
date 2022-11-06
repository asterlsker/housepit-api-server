package com.asterlsker.housepit.api.presentation.auth.dto

import com.asterlsker.housepit.auth.domain.data.TokenData

data class RefreshRes(
    val accessToken: String,
    val refreshToken: String
) {
    companion object {
        fun of(data: TokenData): RefreshRes {
            return RefreshRes(data.accessToken, data.refreshToken)
        }
    }
}
