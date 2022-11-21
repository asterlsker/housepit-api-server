package com.asterlsker.housepit.api.presentation.meet.response

import com.asterlsker.housepit.auth.domain.model.Member
import com.asterlsker.housepit.domain.meet.dto.MeetResult
import com.asterlsker.housepit.domain.meet.dto.MeetsResult


class GetMeetsRes {
    data class Payload(
        val meets: List<Meet>,
        val pageOffset: Int,
        val pageSize: Int
    ) {
        companion object {
            fun of(result: MeetsResult): Payload {
                return Payload(
                    meets = result.meets.map { Meet.of(it) }.toList(),
                    pageOffset = result.pageOffset,
                    pageSize = result.pageSize
                )
            }
        }
    }

    data class Meet(
        val meetId: String,
        val member: Member,
        val title: String
    ) {
        companion object {
            fun of(result: MeetResult): Meet {
                return Meet(
                    meetId = result.meetId,
                    member = result.member,
                    title = result.title
                )
            }
        }
    }
}
