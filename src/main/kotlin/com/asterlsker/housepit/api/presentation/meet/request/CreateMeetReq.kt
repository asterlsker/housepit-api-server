package com.asterlsker.housepit.api.presentation.meet.request

import com.asterlsker.housepit.api.domain.gyul.Gyul
import com.asterlsker.housepit.api.presentation.meet.request.dto.MeetScheduleDto
import javax.validation.constraints.NotBlank

data class CreateMeetReq(
    @NotBlank
    val title: String,
    val content: Gyul?,
    val schedules: List<MeetScheduleDto>
)