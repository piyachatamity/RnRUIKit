package com.amityeko.rnrsdk.category.usecase

import com.amityeko.rnrsdk.category.api.entity.RnRCategoryEntity
import com.amityeko.rnrsdk.category.repository.RnRCategoryRepository
import retrofit2.Response
import timber.log.Timber

class RnRGetCategoryNetworkUseCase(private val categoryRepository: RnRCategoryRepository) {

    suspend fun execute(): Response<List<RnRCategoryEntity>> {
        val responseCategoryList = categoryRepository.apiGetCategory()
        Timber.d("result category: ${responseCategoryList.body().toString()}")

        return responseCategoryList
    }

}