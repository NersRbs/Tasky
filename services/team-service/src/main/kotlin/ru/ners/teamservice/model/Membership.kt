package ru.ners.teamservice.model

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class Membership {
    private var id: Long? = null
    private var teamId: Long? = null
    private var userId: Long? = null
    private var role: Role? = null
    private var status: MembershipStatus? = null
}