package com.asterlsker.housepit.api.domain.meet.result

data class MeetsResult(
    val meets: List<MeetResult>,
    val pageNumber: Int,
    val pageSize: Int
)
