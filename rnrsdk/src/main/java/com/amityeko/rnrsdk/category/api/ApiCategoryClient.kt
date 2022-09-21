package com.amityeko.rnrsdk.category.api

import com.amityeko.common.config.ConfigManager
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiCategoryClient {
    fun getClient(): CategoryService {
        val baseUrl = ConfigManager.getBaseApiUrl()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(CategoryService::class.java)
    }
}