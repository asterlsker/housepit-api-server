package com.asterlsker.housepit.api.presentation.meet.request.dto

import java.time.LocalDateTime
import com.asterlsker.housepit.api.domain.meet.command.dto.MeetScheduleDto as MeetScheduleDomainDto

data class MeetScheduleDto(
    val startAt: LocalDateTime,
    val endAt: LocalDateTime,
    val requiredPeople: Int,
) {
    companion object {
        fun toDomain(data: MeetScheduleDto): MeetScheduleDomainDto {
            return MeetScheduleDomainDto(
                startAt = data.startAt,
                endAt = data.endAt,
                requiredPeople = data.requiredPeople
            )
        }

        fun toDomain(data: List<MeetScheduleDto>): List<MeetScheduleDomainDto> {
            return data.map { toDomain(it) }
        }
    }
}