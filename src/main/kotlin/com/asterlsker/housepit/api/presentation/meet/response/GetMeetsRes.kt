package com.asterlsker.housepit.api.presentation.meet.response

import com.asterlsker.housepit.api.domain.meet.result.MeetResult
import com.asterlsker.housepit.api.domain.meet.result.MeetsResult

data class GetMeetsRes(
    val meets: List<MeetResult>,
    val pageNumber: Int,
    val pageSize: Int
) {
    companion object {
        // fun
        fun of(result: MeetsResult): GetMeetsRes {
            return GetMeetsRes(
                meets = result.meets,
                pageNumber = result.pageNumber,
                pageSize = result.pageSize
            )
        }

    }
}
