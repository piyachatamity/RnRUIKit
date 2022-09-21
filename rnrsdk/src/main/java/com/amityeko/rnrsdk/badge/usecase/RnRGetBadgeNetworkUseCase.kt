package com.amityeko.rnrsdk.badge.usecase

import android.content.Context
import com.amityeko.rnrsdk.badge.api.entity.RnRBadgeBody
import com.amityeko.rnrsdk.badge.repository.RnRBadgeRepository
import retrofit2.Response
import timber.log.Timber

class RnRGetBadgeNetworkUseCase {
    private val context: Context
    private val rnrBadgeRepository: RnRBadgeRepository

    constructor(context: Context) {
        this.context = context
        rnrBadgeRepository = RnRBadgeRepository(context)
    }

    suspend fun execute(cateId: String): Response<RnRBadgeBody> {
        val responseBadge = rnrBadgeRepository.apiGetBadge(cateId)
        Timber.d("response badge: ${responseBadge.raw().toString()}")
        Timber.d("result badge: ${responseBadge.body().toString()}")

        return responseBadge
    }

}