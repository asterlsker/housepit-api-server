package com.asterlsker.housepit.api.domain.meet.command

import org.springframework.data.domain.Pageable

data class GetPrivateMeetsCommand(
    val memberId: String,
    val pageable: Pageable
)