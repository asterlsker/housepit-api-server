package com.asterlsker.housepit.api.domain.meet.entity

import com.asterlsker.housepit.api.domain.gyul.Gyul
import com.asterlsker.housepit.api.domain.meet.MeetScheduleStatus
import com.asterlsker.housepit.core.entity.BaseEntity
import com.vladmihalcea.hibernate.type.json.JsonBinaryType
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import javax.persistence.*

@Entity
@Table(name = "meet")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType::class)
data class MeetEntity(
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", nullable = false)
    val id: String? = null,
    val memberId: String,
    val title: String,
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    val content: Gyul? = null
) : BaseEntity() {
    val deleted: Boolean = false

    //    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "meet")
    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "meet")
    private val meetSchedule = mutableListOf<MeetScheduleEntity>()

    // handler
    val meetSchedules get() = meetSchedule.toList()

    fun addSchedule(meetSchedule: MeetScheduleEntity) {
        this.meetSchedule.add(meetSchedule)
        meetSchedule.meet = this
    }

    // logic
    fun nearestSchedule(): MeetScheduleEntity? {
        if (meetSchedules.isNotEmpty()) return meetSchedules.last()
        return null
    }

    fun completedScheduleCount(): Int {
        var count = 0
        meetSchedules.forEach {
            if (it.status == MeetScheduleStatus.DONE) count++
        }
        return count
    }
}

