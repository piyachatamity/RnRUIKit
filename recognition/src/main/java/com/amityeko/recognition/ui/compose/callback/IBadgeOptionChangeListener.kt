package com.amityeko.recognition.ui.compose.callback

import androidx.lifecycle.LiveData
import com.amityeko.recognition.ui.compose.text.model.BackgroundOption

interface IBadgeOptionChangeListener {
    fun observeBackgroundOption(): LiveData<Int>
    fun onItemClick(item: BackgroundOption)
}