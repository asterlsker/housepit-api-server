package com.asterlsker.housepit.api.presentation.meet.response

import com.asterlsker.housepit.auth.domain.model.Member
import com.asterlsker.housepit.domain.gyul.Gyul
import com.asterlsker.housepit.domain.meet.dto.MeetDetailDto
import com.asterlsker.housepit.domain.meet.type.MeetScheduleStatus
import java.time.LocalDateTime

class GetMeetRes {
    data class Payload(
        val meetId: String,
        val member: Member,
        val title: String,
        val content: Gyul?,
        val schedules: List<Schedule>
    ) {
        companion object {
            fun of(data: MeetDetailDto.Result): Payload = Payload(
                meetId = data.meetId,
                member = data.member,
                title = data.title,
                content = data.content,
                schedules = Schedule.of(data.schedules)
            )
        }
    }

    data class Schedule(
        val startAt: LocalDateTime,
        val endAt: LocalDateTime,
        val requiredPeople: Int,
        val status: MeetScheduleStatus,
    ) {
        companion object {
            fun of(data: MeetDetailDto.Schedule): Schedule = Schedule(
                startAt = data.startAt,
                endAt = data.endAt,
                requiredPeople = data.requiredPeople,
                status = data.status
            )

            fun of(data: List<MeetDetailDto.Schedule>): List<Schedule> = data.map { of(it) }.toList()
        }
    }
}