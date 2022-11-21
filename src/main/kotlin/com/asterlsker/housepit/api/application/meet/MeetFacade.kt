package com.asterlsker.housepit.api.application.meet

import com.asterlsker.housepit.domain.meet.MeetService
import com.asterlsker.housepit.domain.meet.command.CreateMeetCommand
import com.asterlsker.housepit.domain.meet.command.GetMeetCriteria
import com.asterlsker.housepit.domain.meet.command.GetPrivateMeetsCriteria
import com.asterlsker.housepit.domain.meet.command.GetPublicMeetsCriteria
import com.asterlsker.housepit.domain.meet.dto.MeetDetailDto
import com.asterlsker.housepit.domain.meet.dto.MeetsResult
import org.springframework.stereotype.Service

@Service
class MeetFacade(
    private val meetService: MeetService
) {

    fun createMeet(command: CreateMeetCommand) {
        meetService.handleCreateMeetCommand(command)
    }

    fun getMeet(criteria: GetMeetCriteria): MeetDetailDto.Result {
        return meetService.handleGetMeetCriteria(criteria)
    }

    fun getMeets(memberId: String, offset: Int, size: Int): MeetsResult {
        if (memberId.isNotBlank()) {
            val command = GetPrivateMeetsCriteria(memberId, offset, size)
            return meetService.handleGetMeetsCriteria(command)
        }
        val command = GetPublicMeetsCriteria(offset, size)
        return meetService.handleGetMeetsCriteria(command)
    }
}