package com.amityeko.rnrsdk.category.model

import com.amityeko.rnrsdk.category.api.entity.RnRCategoryEntity
import retrofit2.Response

sealed class CategoryListStatus {
    class CategoryListDb(val categoryListEntity: List<RnRCategoryEntity>): CategoryListStatus()
    class CategoryListResponse(val categoryResponse: Response<List<RnRCategoryEntity>>): CategoryListStatus() {
        fun isSuccess(): Boolean {
            return categoryResponse.isSuccessful
        }
        fun getCategoryList(): List<RnRCategoryEntity> {
            if (this.isSuccess()) {
                val data = categoryResponse.body() ?: emptyList()
                return data
            }
            return emptyList()
        }

        fun getError(): okhttp3.Response {
            return categoryResponse.raw()
        }
    }

}