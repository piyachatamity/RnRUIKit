package com.amityeko.rnrsdk.badge.repository

import android.content.Context
import com.amityeko.rnrsdk.auth.repository.AuthRepository
import com.amityeko.rnrsdk.badge.api.ApiBadgeClient
import com.amityeko.rnrsdk.badge.api.entity.RnRBadgeBody
import retrofit2.Response
import timber.log.Timber

class RnRBadgeRepository {
    private val context: Context
    private val client = ApiBadgeClient().getClient()

    private val authRepository: AuthRepository

    constructor(context: Context) {
        this.context = context
        authRepository = AuthRepository(context)
    }

    suspend fun apiGetBadge(cateId: String): Response<RnRBadgeBody> {
        val authTokenModel = authRepository.loadToken()
        val token = "Bearer " + authTokenModel.accessToken

        val responseBadge = client.getBadge(token, cateId, 5, null)

        Timber.d("result badge: ${responseBadge.body().toString()}")
        return responseBadge
    }

}