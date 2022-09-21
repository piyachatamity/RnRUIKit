package com.amityeko.recognition.ui.compose.text.model

import androidx.annotation.DrawableRes

data class BackgroundOption(
    @DrawableRes val resIdBg: Int,
    @DrawableRes val resIdBgOption: Int,
    val colorStart: String,
    val colorEnd: String,
)