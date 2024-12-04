package ru.ners.teamservice.service

import ru.ners.teamservice.model.Team

interface TeamService {
    fun createTeam(team: Team): Long
    fun getTeamById(id: Long): Team?
    fun getTeamsByMemberId(memberId: Long): List<Team>
    fun updateTeam(team: Team)
    fun deleteTeam(id: Long)
}