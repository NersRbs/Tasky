package ru.ners.teamservice.model

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class Team {
    private var id: Long? = null
    private var name: String? = null
    private var membersIds: List<Long>? = null
}