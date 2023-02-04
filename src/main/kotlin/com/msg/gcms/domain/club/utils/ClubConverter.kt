package com.msg.gcms.domain.club.utils

import com.msg.gcms.domain.club.domain.entity.Club
import com.msg.gcms.domain.club.enums.ClubType
import com.msg.gcms.domain.club.enums.Scope
import com.msg.gcms.domain.club.presentation.data.dto.*
import com.msg.gcms.domain.club.presentation.data.request.CreateClubRequest
import com.msg.gcms.domain.club.presentation.data.request.UpdateClubRequest
import com.msg.gcms.domain.club.presentation.data.response.ClubListResponseDto
import com.msg.gcms.domain.club.presentation.data.response.DetailClubResponseDto
import com.msg.gcms.domain.user.domain.entity.User

interface ClubConverter {
    fun toDto(createClubRequest: CreateClubRequest): ClubDto
    fun toEntity(clubDto: ClubDto, user: User): Club
    fun toDto(type: ClubType): ClubTypeDto
    fun toDto(club: Club): ClubListDto
    fun toDto(dto: User): DetailClubDto.UserDto
    fun toDto(club: Club, clubMemberDto:List<DetailClubDto.UserDto>, clubImages: List<String>, scope: Scope, isApplicant: Boolean): DetailClubDto
    fun toResponseDto(dto: ClubListDto): ClubListResponseDto
    fun toResponseDto(dto: DetailClubDto.UserDto): DetailClubResponseDto.UserResponseDto
    fun toResponseDto(dto: DetailClubDto, memberResponseDto: List<DetailClubResponseDto.UserResponseDto>): DetailClubResponseDto
    fun toDto(updateClubRequest: UpdateClubRequest): ClubDto
    fun toStatusDto(club: Club): ClubStatusDto
}