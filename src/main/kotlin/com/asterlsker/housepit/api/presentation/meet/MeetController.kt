package com.asterlsker.housepit.api.presentation.meet

import com.asterlsker.housepit.api.application.meet.MeetFacade
import com.asterlsker.housepit.api.common.MEET
import com.asterlsker.housepit.api.common.MEETS
import com.asterlsker.housepit.api.common.MEET_MEETID
import com.asterlsker.housepit.api.common.response.CommonResponse
import com.asterlsker.housepit.api.presentation.common.utils.RequestContext
import com.asterlsker.housepit.api.presentation.meet.request.CreateMeetReq
import com.asterlsker.housepit.api.presentation.meet.request.GetMeetsReq
import com.asterlsker.housepit.api.presentation.meet.request.dto.MeetScheduleDto
import com.asterlsker.housepit.api.presentation.meet.response.GetMeetRes
import com.asterlsker.housepit.api.presentation.meet.response.GetMeetsRes
import com.asterlsker.housepit.core.annotation.Authentication
import com.asterlsker.housepit.core.enums.AuthenticationScope
import com.asterlsker.housepit.domain.meet.command.CreateMeetCommand
import com.asterlsker.housepit.domain.meet.command.GetMeetCriteria
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

    @GetMapping(MEET_MEETID)
    fun getMeet(@PathVariable("meet-id") meetId: String): CommonResponse<GetMeetRes.Payload> {
        val criteria = GetMeetCriteria(
            meetId = meetId
        )
        val data = meetFacade.getMeet(criteria)
        val payload = GetMeetRes.Payload.of(data)
        return CommonResponse.success(payload)
    }

    @Authentication(scope = AuthenticationScope.OPTIONAL)
    @GetMapping(MEETS)
    fun getMeets(@Valid request: GetMeetsReq): CommonResponse<GetMeetsRes.Payload> {
        val memberId = RequestContext.getMemberId()
        val data = meetFacade.getMeets(memberId, request.offset, request.size)
        val payload = GetMeetsRes.Payload.of(data)
        return CommonResponse.success(payload)
    }

    @PutMapping(MEET)
    fun updatedMeet() {
        val memberId = RequestContext.getMemberId()
    }
}