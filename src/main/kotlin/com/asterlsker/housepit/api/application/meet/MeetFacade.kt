package com.asterlsker.housepit.api.application.meet

import com.asterlsker.housepit.domain.meet.command.CreateMeetCommand
import com.asterlsker.housepit.domain.meet.criteria.GetPrivateMeetsCriteria
import com.asterlsker.housepit.domain.meet.criteria.GetPublicMeetsCriteria
import com.asterlsker.housepit.domain.meet.result.MeetsResult
import com.asterlsker.housepit.domain.meet.service.MeetService
import org.springframework.stereotype.Service

@Service
class MeetFacade(
    private val meetService: MeetService
) {

    fun createMeet(command: CreateMeetCommand) {
        meetService.handleCreateMeetCommand(command)
    }

    fun getMeets(memberId: String, offset: Int, size: Int): MeetsResult {
        if (memberId.isNotBlank()) {
            val command = GetPrivateMeetsCriteria(memberId, offset, size)
            return meetService.handleGetMeets(command)
        }
        val command = GetPublicMeetsCriteria(offset, size)
        return meetService.handleGetMeets(command)
    }
}