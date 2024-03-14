package com.msg.gcms.domain.attendance.service.impl

import com.msg.gcms.domain.attendance.exception.ScheduleNotFountException
import com.msg.gcms.domain.attendance.presentation.data.dto.SearchScheduleDto
import com.msg.gcms.domain.attendance.repository.AttendanceRepository
import com.msg.gcms.domain.attendance.repository.ScheduleRepository
import com.msg.gcms.domain.attendance.service.QueryCurrentAttendConditionService
import com.msg.gcms.domain.attendance.util.AttendanceConverter
import com.msg.gcms.domain.club.domain.repository.ClubRepository
import com.msg.gcms.domain.club.exception.ClubNotFoundException
import com.msg.gcms.domain.club.exception.HeadNotSameException
import com.msg.gcms.domain.user.domain.entity.User
import com.msg.gcms.domain.attendance.presentation.data.dto.UserAttendanceStatusDto
import com.msg.gcms.global.util.UserUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.LocalTime

@Service
@Transactional(rollbackFor = [Exception::class], readOnly = true)
class QueryCurrentAttendConditionServiceImpl(
    private val userUtil: UserUtil,
    private val clubRepository: ClubRepository,
    private val scheduleRepository: ScheduleRepository,
    private val attendanceRepository: AttendanceRepository,
    private val attendanceConverter: AttendanceConverter
) : QueryCurrentAttendConditionService {
    override fun execute(searchScheduleDto: SearchScheduleDto): List<UserAttendanceStatusDto> {
        val user: User = userUtil.fetchCurrentUser()

        val club = clubRepository.findByIdOrNull(searchScheduleDto.clubId) ?: throw ClubNotFoundException()

        if (user != club.user)
            throw HeadNotSameException()

        val schedule = scheduleRepository.queryByDateAndPeriod(club, searchScheduleDto.date, searchScheduleDto.period) ?: throw ScheduleNotFountException()

        val attendance = attendanceRepository.findAllBySchedule(schedule)

        return attendance.map { attendanceConverter.toDto(it.user, it) }
    }
}