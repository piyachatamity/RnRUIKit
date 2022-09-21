package com.amityeko.recognition.ui.home.callback

import androidx.lifecycle.LiveData
import com.amityeko.rnrsdk.badge.api.entity.RnRBadgeEntity

interface IThumbsUpListListener {
    fun onItemClick(item: RnRBadgeEntity)
    fun selectedItem() : LiveData<RnRBadgeEntity>
}