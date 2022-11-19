package com.asterlsker.housepit.api.domain.meet.command.dto

import com.asterlsker.housepit.api.domain.meet.entity.MeetScheduleEntity
import java.time.LocalDateTime

data class MeetScheduleDto(
    val startAt: LocalDateTime,
    val endAt: LocalDateTime,
    val requiredPeople: Int,
) {
    companion object {

        fun toEntity(data: MeetScheduleDto): MeetScheduleEntity {
            return MeetScheduleEntity(
                startAt = data.startAt,
                endAt = data.endAt,
                requiredPeople = data.requiredPeople
            )
        }

        fun toEntity(data: List<MeetScheduleDto>): List<MeetScheduleEntity> {
            return data.map { toEntity(it) }
        }
    }
}
