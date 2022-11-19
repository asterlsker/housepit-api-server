package com.asterlsker.housepit.api.domain.meet.command

import org.springframework.data.domain.Pageable

data class GetPublicMeetsCommand(
    val pageable: Pageable
)