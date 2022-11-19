package com.asterlsker.housepit.api.domain.meet.result

import com.asterlsker.housepit.api.domain.meet.entity.MeetEntity
import com.asterlsker.housepit.auth.domain.model.Member

data class MeetResult(
    val meetId: String,
    val member: Member,
    val title: String
) {
    companion object {
        fun of(entity: MeetEntity): MeetResult {
            return MeetResult(
                meetId = entity.id!!,
                member = Member(
                    memberId = entity.memberId,
                ),
                title = entity.title
            )
        }
    }
}
