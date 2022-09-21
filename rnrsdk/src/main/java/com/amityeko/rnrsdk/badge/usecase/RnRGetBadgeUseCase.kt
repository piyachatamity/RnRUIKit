package com.amityeko.rnrsdk.badge.usecase

import android.content.Context
import com.amityeko.rnrsdk.badge.model.BadgeListState

class RnRGetBadgeUseCase {
    private val context: Context
    private val getBadgeNetworkUseCase: RnRGetBadgeNetworkUseCase
    private lateinit var badgeListState: BadgeListState

    constructor(context: Context) {
        this.context = context
        getBadgeNetworkUseCase = RnRGetBadgeNetworkUseCase(context)
    }

    suspend fun execute(cateId: String): BadgeListState {
        val response = getBadgeNetworkUseCase.execute(cateId)
        badgeListState = BadgeListState.BadgeListResponse(response)
        return badgeListState
    }
}