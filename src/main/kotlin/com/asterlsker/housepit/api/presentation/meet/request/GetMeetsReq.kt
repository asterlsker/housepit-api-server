package com.asterlsker.housepit.api.presentation.meet.request

data class GetMeetsReq(
    val offset: Int = 1,
    val size: Int = 20
)
