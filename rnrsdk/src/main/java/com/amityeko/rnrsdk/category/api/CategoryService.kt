package com.amityeko.rnrsdk.category.api

import com.amityeko.rnrsdk.category.api.entity.RnRCategoryEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface CategoryService {

    @GET("categories")
    suspend fun getCategory(@Header("Authorization") token: String): Response<List<RnRCategoryEntity>>
}