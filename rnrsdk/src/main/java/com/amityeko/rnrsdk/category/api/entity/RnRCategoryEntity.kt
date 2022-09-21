package com.amityeko.rnrsdk.category.api.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RnRCategoryEntity (
    val id: String,
    val name: String,
    val color: String,
): Parcelable