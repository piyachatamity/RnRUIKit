package com.amityeko.rnrsdk.category.usecase

import com.amityeko.rnrsdk.category.model.CategoryListStatus
import com.amityeko.rnrsdk.category.repository.RnRCategoryRepository

class RnRGetCategoryUseCase {
    private val getCategoryNetworkUseCase: RnRGetCategoryNetworkUseCase
    private lateinit var cateListStatus: CategoryListStatus

    constructor(rnrCategoryRepository: RnRCategoryRepository) {
        getCategoryNetworkUseCase = RnRGetCategoryNetworkUseCase(rnrCategoryRepository)
    }

    suspend fun execute(): CategoryListStatus {
        val response = getCategoryNetworkUseCase.execute()
        cateListStatus = CategoryListStatus.CategoryListResponse(response)

        return cateListStatus
    }

}