package com.amityeko.rnrsdk.auth.api.dto.auth_session

data class AuthSessionDto (
        var deviceId: String,
        var displayName: String,
        var userId: String,
        var deviceInfo: DeviceInfoDto,
)