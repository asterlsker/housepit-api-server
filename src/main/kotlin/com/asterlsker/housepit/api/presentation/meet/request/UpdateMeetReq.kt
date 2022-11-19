package com.asterlsker.housepit.api.presentation.meet.request

import com.asterlsker.housepit.api.domain.gyul.Gyul
import javax.validation.constraints.NotBlank

data class UpdateMeetReq(
    @NotBlank
    val title: String,
    val content: Gyul?,
)