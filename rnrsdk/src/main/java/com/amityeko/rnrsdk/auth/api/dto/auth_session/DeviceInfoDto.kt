package com.amityeko.rnrsdk.auth.api.dto.auth_session

import com.amityeko.rnrsdk.auth.api.dto.auth_session.AppVersionDto

data class DeviceInfoDto (
    val type: String,
    val model: String,
    val version: AppVersionDto
    )