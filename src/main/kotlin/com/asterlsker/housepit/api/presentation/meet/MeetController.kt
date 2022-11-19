package com.asterlsker.housepit.api.presentation.meet

import com.asterlsker.housepit.api.application.meet.MeetFacade
import com.asterlsker.housepit.api.common.MEET
import com.asterlsker.housepit.api.common.MEETS
import com.asterlsker.housepit.api.common.response.CommonResponse
import com.asterlsker.housepit.api.domain.meet.command.CreateMeetCommand
import com.asterlsker.housepit.api.presentation.common.utils.RequestContext
import com.asterlsker.housepit.api.presentation.meet.request.CreateMeetReq
import com.asterlsker.housepit.api.presentation.meet.request.dto.MeetScheduleDto
import com.asterlsker.housepit.api.presentation.meet.response.GetMeetsRes
import com.asterlsker.housepit.core.annotation.Authentication
import com.asterlsker.housepit.core.enums.AuthenticationScope
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class MeetController(
    private val meetFacade: MeetFacade
) {
    @Value("\${a}")
    private val a: String? = null
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
    fun getMeets(
        @PageableDefault(page = 0, size = 20) pageable: Pageable
    ): CommonResponse<GetMeetsRes> {
        val memberId = RequestContext.getMemberId()
        val data = meetFacade.getMeets(memberId, pageable)
        val formattedData = GetMeetsRes.of(data)
        return CommonResponse.success(formattedData)
    }

    @PutMapping(MEET)
    fun getMeet() {
        val memberId = RequestContext.getMemberId()
        print("AVSVS: $a")
    }
}