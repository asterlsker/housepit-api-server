package com.asterlsker.housepit.api.domain.meet.entity

import com.asterlsker.housepit.api.domain.meet.MeetScheduleStatus
import com.asterlsker.housepit.core.entity.BaseEntity
import java.time.LocalDateTime
import javax.persistence.*


@Entity
@Table(name = "meet_schedule")
data class MeetScheduleEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    val id: Long? = null,
    val startAt: LocalDateTime,
    val endAt: LocalDateTime,
    val requiredPeople: Int,
) : BaseEntity() {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meet_id", referencedColumnName = "id")
    var meet: MeetEntity? = null

    @Enumerated(EnumType.STRING)
    val status: MeetScheduleStatus = MeetScheduleStatus.RECRUIT

    val deleted: Boolean = false

    // handler
    // logic

}
