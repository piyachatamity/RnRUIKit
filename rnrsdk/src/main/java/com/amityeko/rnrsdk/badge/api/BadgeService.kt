package com.amityeko.rnrsdk.badge.api

import com.amityeko.rnrsdk.badge.api.entity.RnRBadgeBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface BadgeService {

    @GET("badges")
    suspend fun getBadge(
        @Header("Authorization") token: String,
        @Query("categoryId") categoryId: String,
        @Query("limit") limit: Int,
        @Query("paginationToken") paginationToken: String?,
    ): Response<RnRBadgeBody>
}