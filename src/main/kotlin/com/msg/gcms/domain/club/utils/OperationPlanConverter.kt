package com.msg.gcms.domain.club.utils

import com.msg.gcms.domain.club.presentation.data.dto.OperationPlanDto
import com.msg.gcms.domain.club.presentation.data.request.CreateOperationPlanRequest

interface OperationPlanConverter {
    fun toDto(createOperationPlanRequest: CreateOperationPlanRequest): OperationPlanDto
    fun toDto(monthlyPlan: List<CreateOperationPlanRequest.CreateMonthlyPlanRequest>): List<OperationPlanDto.MonthlyPlanDto>
}