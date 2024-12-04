package ru.ners.teamservice.service

import ru.ners.teamservice.model.Membership
import ru.ners.teamservice.model.MembershipStatus

interface MembershipService {
    fun addMembership(membership: Membership): Long
    fun getMembershipsByTeamIdAndStatus(teamId: Long, status: MembershipStatus): List<Membership>
    fun updateMembership(membership: Membership)
    fun deleteMembershipsByTeamId(teamId: Long)
}