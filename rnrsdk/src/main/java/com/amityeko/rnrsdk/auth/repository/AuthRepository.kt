package com.amityeko.rnrsdk.auth.repository

import android.content.Context
import com.amityeko.common.cache_store.CacheStore
import com.amityeko.rnrsdk.auth.api.ApiAuthClient
import com.amityeko.rnrsdk.auth.api.dto.auth_session.AuthSessionDto
import com.amityeko.rnrsdk.auth.api.entity.AuthSessionEntity
import com.amityeko.rnrsdk.auth.data.AuthTokenModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class AuthRepository {
    private val context: Context
    private val cacheStore: CacheStore
    private val client = ApiAuthClient().getClient()


    constructor(context: Context) {
        this.context = context
        this.cacheStore = CacheStore(context)
    }

    fun saveToken(authTokenModel: AuthTokenModel) {
        cacheStore.saveAccessToken(authTokenModel.accessToken)
        cacheStore.saveRefreshToken(authTokenModel.refreshToken)
    }

    fun loadToken(): AuthTokenModel {
        val accessToken = cacheStore.loadAccessToken()?: ""
        val refreshToken = cacheStore.loadRefreshToken()?: ""
        return AuthTokenModel(accessToken, refreshToken)
    }

    fun clearToken() {
        cacheStore.clearValue()
    }

    suspend fun apiRegister(apiKey: String, authSessionDto: AuthSessionDto): Response<AuthSessionEntity> {
        return withContext(Dispatchers.IO) {
            return@withContext client.authSession(apiKey, authSessionDto)
        }
    }
}