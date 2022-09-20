package com.amityeko.rnr.usecase

import android.content.Context
import android.provider.Settings
import com.amityeko.rnrsdk.auth.api.dto.factory.AuthSessionDtoFactory
import com.amityeko.rnrsdk.auth.api.entity.AuthSessionEntity
import com.amityeko.rnrsdk.auth.data.AuthTokenModel
import com.amityeko.rnrsdk.auth.repository.AppConfigRepository
import com.amityeko.rnrsdk.auth.repository.AuthRepository
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.processors.PublishProcessor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Response
import timber.log.Timber

class RegisterDeviceUseCase {
    private val context: Context
    private val scope = CoroutineScope(Job() + Dispatchers.IO)

    private val appConfigRepository: AppConfigRepository
    private val authRepository: AuthRepository

    constructor(context: Context) {
        this.context = context

        appConfigRepository = AppConfigRepository(context)
        authRepository = AuthRepository(context)
    }

    fun execute(apiKey: String, userDisplayName: String, userId: String): Flowable<Boolean> {
        val registerRelay = PublishProcessor.create<Boolean>()

        scope.launch {
            clearAllData()
            saveConfig(apiKey)

            val responseAuthSessionEntity = apiAuth(apiKey, userDisplayName, userId)
            if (responseAuthSessionEntity.isSuccessful) {
                val authTokenEntity = responseAuthSessionEntity.body()
                if (authTokenEntity != null) {
                    saveToken(authTokenEntity)
                }

                registerRelay.onNext(true)
            } else {
                registerRelay.onError(Throwable(responseAuthSessionEntity.raw().toString()))
            }
        }

        return registerRelay
    }

    private fun saveConfig(apiKey: String) {
        appConfigRepository.saveApiKey(apiKey)
    }

    private suspend fun apiAuth(apiKey: String, userDisplayName: String, userId: String): Response<AuthSessionEntity> {
        val deviceId = Settings.Secure.getString(context?.contentResolver ?: null, Settings.Secure.ANDROID_ID)
        val authFactory = AuthSessionDtoFactory()
        val authSessionDto = authFactory.setDeviceId(deviceId)
            .setDisplayName(userDisplayName)
            .setUserId(userId)
            .create()

        val responseAuthSessionEntity = authRepository.apiRegister(apiKey, authSessionDto)
        Timber.d("connect auth complete result: ${responseAuthSessionEntity.body().toString()}")

        return responseAuthSessionEntity
    }

    private fun saveToken(authSessionEntity: AuthSessionEntity) {
        val authTokenModel = AuthTokenModel(authSessionEntity.accessToken, authSessionEntity.refreshToken)
        authRepository.saveToken(authTokenModel)
    }

    private fun clearAllData() {
        appConfigRepository.clearApiKey()
        authRepository.clearToken()
    }

}