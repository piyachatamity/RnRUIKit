package com.amityeko.recognition.ui.home.callback

import androidx.lifecycle.LiveData
import com.amityeko.rnrsdk.badge.api.entity.RnRBadgeEntity

interface IDialogHomeListener {
    fun onLoadedBadge(isEmpty: Boolean)
    fun onSelectedBadge(rnrBadgeEntity: RnRBadgeEntity)
    fun getLiveSeletedBadge(): LiveData<RnRBadgeEntity>
}