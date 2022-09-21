package com.amityeko.rnrsdk.badge.api

import com.amityeko.common.config.ConfigManager
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiBadgeClient {
    fun getClient(): BadgeService {
        val baseUrl = ConfigManager.getBaseApiUrl()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(BadgeService::class.java)
    }
}