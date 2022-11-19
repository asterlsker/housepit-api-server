package com.asterlsker.housepit.api.domain.meet

import com.asterlsker.housepit.api.domain.gyul.Gyul
import com.asterlsker.housepit.api.domain.meet.command.CreateMeetCommand
import com.asterlsker.housepit.api.domain.meet.command.GetPrivateMeetsCommand
import com.asterlsker.housepit.api.domain.meet.command.GetPublicMeetsCommand
import com.asterlsker.housepit.api.domain.meet.command.dto.MeetScheduleDto
import com.asterlsker.housepit.api.domain.meet.result.MeetsResult
import com.asterlsker.housepit.api.domain.meet.entity.MeetEntity
import com.asterlsker.housepit.api.domain.meet.entity.MeetScheduleEntity
import com.asterlsker.housepit.api.domain.meet.result.MeetResult
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MeetService(private val meetRepository: MeetRepository) {

    /**
     * 새로운 모임 생성
     */
    @Transactional
    fun createMeet(memberId: String, title: String, content: Gyul?, schedules: List<MeetScheduleEntity>) {
        val meet = MeetEntity(memberId = memberId, title = title, content = content)
        schedules.forEach { meet.addSchedule(it) }
        meetRepository.save(meet)
    }

    @Transactional
    fun handleCreateMeetCommand(command: CreateMeetCommand) {
        val meet = MeetEntity(memberId = command.memberId, title = command.title, content = command.content)
        MeetScheduleDto.toEntity(command.schedules).forEach { meet.addSchedule(it) }
        meetRepository.save(meet)
    }

    @Transactional
    fun handleGetMeets(command: GetPrivateMeetsCommand): MeetsResult {
        val meets = meetRepository.findByMemberIdOrderByCreatedAt(command.memberId, command.pageable)
        return handleGetMeetsCore(meets)
    }

    @Transactional
    fun handleGetMeets(command: GetPublicMeetsCommand): MeetsResult {
        val meets = meetRepository.findByOrderByCreatedAt(command.pageable)
        return handleGetMeetsCore(meets)
    }

    // duplication
    private fun handleGetMeetsCore(meets: Page<MeetEntity>): MeetsResult {
        val data = meets.content.map { MeetResult.of(it) }
        return MeetsResult(
            meets = data,
            pageNumber = meets.pageable.pageNumber,
            pageSize = meets.pageable.pageSize,
        )
    }
}
