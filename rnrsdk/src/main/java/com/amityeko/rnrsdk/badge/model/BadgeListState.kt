package com.amityeko.rnrsdk.badge.model

import com.amityeko.rnrsdk.badge.api.entity.RnRBadgeBody
import com.amityeko.rnrsdk.badge.api.entity.RnRBadgeEntity
import retrofit2.Response

sealed class BadgeListState {
    class BadgeListDb(val badgeList: List<RnRBadgeEntity>): BadgeListState()
    class BadgeListResponse(private val badgeResponse: Response<RnRBadgeBody>): BadgeListState() {

        fun isSuccess(): Boolean {
            return badgeResponse.isSuccessful
        }
        fun getBadgeList(): List<RnRBadgeEntity> {
            if (this.isSuccess()) {
                val data = badgeResponse.body()?.data ?: emptyList()
                return data
            }
            return emptyList()
        }

        fun getError(): okhttp3.Response {
            return badgeResponse.raw()
        }
    }
}