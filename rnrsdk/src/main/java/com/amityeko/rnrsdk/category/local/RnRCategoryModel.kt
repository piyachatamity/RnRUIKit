package com.amityeko.rnrsdk.category.local

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RnRCategoryModel (
    val id: String,
    val name: String,
    val color: String,
) : Parcelable