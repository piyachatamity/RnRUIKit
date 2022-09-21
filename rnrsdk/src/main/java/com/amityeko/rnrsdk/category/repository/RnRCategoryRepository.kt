package com.amityeko.rnrsdk.category.repository

import android.content.Context
import com.amityeko.rnrsdk.auth.repository.AuthRepository
import com.amityeko.rnrsdk.category.api.ApiCategoryClient
import com.amityeko.rnrsdk.category.api.entity.RnRCategoryEntity
import com.amityeko.rnrsdk.category.local.RnRCategoryModel
import retrofit2.Response
import timber.log.Timber

class RnRCategoryRepository {
    private val context: Context
    private val client = ApiCategoryClient().getClient()
    private val authRepository: AuthRepository

    constructor(context: Context) {
        this.context = context
        authRepository = AuthRepository(context)
    }

    suspend fun apiGetCategory(): Response<List<RnRCategoryEntity>> {
        val authTokenModel = authRepository.loadToken()
        val token = "Bearer " + authTokenModel.accessToken ?: ""

        val responseCategoryList = client.getCategory(token)

        Timber.d("result category: ${responseCategoryList.body().toString()}")
        return responseCategoryList
    }
}