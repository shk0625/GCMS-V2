package com.msg.gcms.testUtils

import com.msg.gcms.domain.club.domain.entity.Club
import com.msg.gcms.domain.club.domain.entity.enums.ClubType
import com.msg.gcms.domain.clubMember.domain.entity.ClubMember
import com.msg.gcms.domain.clubMember.domain.entity.enums.MemberScope
import com.msg.gcms.domain.clubMember.presentation.data.dto.ClubMemberDto
import com.msg.gcms.domain.user.domain.entity.User
import kotlin.random.Random

object ClubMemberDataUtil {
    fun entity(scope: MemberScope, club: Club) = ClubMember(
        id = Random.nextLong(),
        scope = scope,
        club = club,
        user = TestUtils.data().user().entity()
    )

    fun clubMemberDto(user: User, scope: MemberScope) = ClubMemberDto(
        uuid = user.id,
        email = user.email,
        name = user.nickname,
        grade = user.grade,
        classNum = user.classNum,
        number = user.number,
        profileImg = user.profileImg,
        scope = scope
    )
}