package com.asterlsker.housepit.api.application.meet

import com.asterlsker.housepit.api.domain.meet.MeetService
import com.asterlsker.housepit.api.domain.meet.command.CreateMeetCommand
import com.asterlsker.housepit.api.domain.meet.command.GetPrivateMeetsCommand
import com.asterlsker.housepit.api.domain.meet.command.GetPublicMeetsCommand
import com.asterlsker.housepit.api.domain.meet.result.MeetsResult
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class MeetFacade(
    private val meetService: MeetService
) {

    fun createMeet(command: CreateMeetCommand) {
        meetService.handleCreateMeetCommand(command)
    }

    fun getMeets(memberId: String, pageable: Pageable): MeetsResult {
        if (memberId.isNotBlank()) {
            val command = GetPrivateMeetsCommand(memberId, pageable)
            return meetService.handleGetMeets(command)
        }
        val command = GetPublicMeetsCommand(pageable)
        return meetService.handleGetMeets(command)
    }
}