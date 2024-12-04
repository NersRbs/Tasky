package ru.ners.teamservice.repository

import ru.ners.teamservice.model.Membership

interface MembershipRepository {
    fun createMembership(teamId: Long, memberId: Long): Long
    fun getMembershipByTeamIdAndStatus(teamId: Long, status: String): List<Membership>
    fun updateMembership(membership: Membership)
    fun deleteMembershipsByTeamId(teamId: Long)
}