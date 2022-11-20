package com.asterlsker.housepit.api.presentation.meet

import com.asterlsker.housepit.api.application.meet.MeetFacade
import com.asterlsker.housepit.api.common.MEET
import com.asterlsker.housepit.api.common.MEETS
import com.asterlsker.housepit.api.common.response.CommonResponse
import com.asterlsker.housepit.api.presentation.common.utils.RequestContext
import com.asterlsker.housepit.api.presentation.meet.request.CreateMeetReq
import com.asterlsker.housepit.api.presentation.meet.request.GetMeetsReq
import com.asterlsker.housepit.api.presentation.meet.request.dto.MeetScheduleDto
import com.asterlsker.housepit.api.presentation.meet.response.GetMeetsRes
import com.asterlsker.housepit.core.annotation.Authentication
import com.asterlsker.housepit.core.enums.AuthenticationScope
import com.asterlsker.housepit.domain.meet.command.CreateMeetCommand
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class MeetController(
    private val meetFacade: MeetFacade
) {
    @PostMapping(MEET)
    fun createMeet(@RequestBody @Valid request: CreateMeetReq): CommonResponse<Unit> {
        val command = CreateMeetCommand(
            memberId = RequestContext.getMemberId(),
            title = request.title,
            content = request.content,
            schedules = MeetScheduleDto.toDomain(request.schedules)
        )
        meetFacade.createMeet(command)
        return CommonResponse.success()
    }

    @Authentication(scope = AuthenticationScope.OPTIONAL)
    @GetMapping(MEETS)
    fun getMeets(@Valid request: GetMeetsReq): CommonResponse<GetMeetsRes> {
        val memberId = RequestContext.getMemberId()
        val data = meetFacade.getMeets(memberId, request.offset, request.size)
        val formattedData = GetMeetsRes.of(data)
        return CommonResponse.success(formattedData)
    }

    @PutMapping(MEET)
    fun getMeet() {
        val memberId = RequestContext.getMemberId()
    }
}