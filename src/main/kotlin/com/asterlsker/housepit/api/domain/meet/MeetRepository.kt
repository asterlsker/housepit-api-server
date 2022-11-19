package com.asterlsker.housepit.api.domain.meet

import com.asterlsker.housepit.api.domain.meet.entity.MeetEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface MeetRepository : JpaRepository<MeetEntity, Long> {
    fun findByMemberIdOrderByCreatedAt(memberId: String, pageable: Pageable): Page<MeetEntity>
    fun findByOrderByCreatedAt(pageable: Pageable): Page<MeetEntity>
}

