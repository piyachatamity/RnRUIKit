package com.amityeko.rnrsdk.badge.api.entity

import android.os.Parcelable
import com.amityeko.common.config.ConfigManager
import com.amityeko.rnrsdk.category.api.entity.RnRCategoryEntity
import kotlinx.parcelize.Parcelize
import timber.log.Timber

@Parcelize
data class RnRBadgeEntity (
    val id: String,
    val thumbnail: String,
    val animation: String,
    val name: String,
    val rewards: List<RnRBadgeRewards>,
    val category: RnRCategoryEntity,
    val updatedAt: String,
) : Parcelable {
    fun getPoint(): Int {
        Timber.d("rewards: ${rewards.toString()}")
        if (rewards.isNotEmpty()) {
            return rewards[0].value
        }
        return 0
    }

    fun getImageUrl(): String {
        return ConfigManager.getImagePath() + "$thumbnail?size=medium"
    }
}

@Parcelize
data class RnRBadgeRewards (
    val type: String,
    val value: Int
): Parcelable