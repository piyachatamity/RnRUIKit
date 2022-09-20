package com.amityeko.common.config

import com.amityeko.rnr.common.config.android.AppConfig
import com.amityeko.rnr.common.config.api.RecognitionConfigDev
import com.amityeko.rnr.common.config.api.RecognitionConfigStg

object ConfigManager {
    private fun getEnvConfig(key: String): String {
        return ""
    }

    fun getBaseApiUrl(): String {
        return RecognitionConfigDev.host + RecognitionConfigDev.pathApi
    }

    fun getAppVersion(): String {
        return AppConfig.appVersion
    }

    fun getImagePath(): String {
        return RecognitionConfigDev.host + RecognitionConfigDev.imagePath
    }
}