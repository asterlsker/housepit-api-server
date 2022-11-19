package com.asterlsker.housepit.api.presentation.auth.data

import com.asterlsker.housepit.core.enums.OAuth2Provider


data class SignInReq(
    val provider: OAuth2Provider,
    val idToken: String
)