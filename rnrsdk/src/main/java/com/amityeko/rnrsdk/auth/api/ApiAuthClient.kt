package com.amityeko.rnrsdk.auth.api

import com.amityeko.common.config.ConfigManager
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiAuthClient {
    fun getClient(): AuthService {
        val baseUrl = ConfigManager.getBaseApiUrl()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(AuthService::class.java)
    }
}