package com.asterlsker.housepit.api.domain.meet.command

import com.asterlsker.housepit.api.domain.gyul.Gyul
import com.asterlsker.housepit.api.domain.meet.command.dto.MeetScheduleDto

data class CreateMeetCommand(
    val memberId: String,
    val title: String,
    val content: Gyul?,
    val schedules: List<MeetScheduleDto>
)