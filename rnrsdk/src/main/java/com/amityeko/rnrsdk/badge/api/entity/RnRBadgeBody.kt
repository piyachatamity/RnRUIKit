package com.amityeko.rnrsdk.badge.api.entity

data class RnRBadgeBody (
    val data: List<RnRBadgeEntity>,
    val cursor: BadgeCursor
)

data class BadgeCursor (
    val next: String,
    val previous: String,
)