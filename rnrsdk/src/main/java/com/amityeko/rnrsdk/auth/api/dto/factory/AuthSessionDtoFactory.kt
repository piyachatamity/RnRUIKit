package com.amityeko.rnrsdk.auth.api.dto.factory

import android.os.Build.VERSION
import com.amityeko.common.config.ConfigManager
import com.amityeko.rnrsdk.auth.api.dto.auth_session.AppVersionDto
import com.amityeko.rnrsdk.auth.api.dto.auth_session.AuthSessionDto
import com.amityeko.rnrsdk.auth.api.dto.auth_session.DeviceInfoDto
import com.jaredrummler.android.device.DeviceName

class AuthSessionDtoFactory {
    private var authSessionDto: AuthSessionDto
    private var deviceInfoDto: DeviceInfoDto
    private var appVersionDto: AppVersionDto

    constructor() {
        val os = VERSION.BASE_OS
        val sdkVersion = ConfigManager.getAppVersion()
        val deviceModel = DeviceName.getDeviceName()

        appVersionDto = AppVersionDto(os, sdkVersion)
        deviceInfoDto = DeviceInfoDto("android", deviceModel, appVersionDto)
        authSessionDto = AuthSessionDto("", "", "", deviceInfoDto)
    }

    fun create(): AuthSessionDto {
        return authSessionDto
    }

    fun setDeviceId(deviceId: String): AuthSessionDtoFactory {
        authSessionDto.deviceId = deviceId
        return this
    }

    fun setDisplayName(displayName: String): AuthSessionDtoFactory {
        authSessionDto.displayName = displayName
        return this
    }

    fun setUserId(userId: String): AuthSessionDtoFactory {
        authSessionDto.userId = userId
        return this
    }

}