package com.amityeko.common.cache_store

import android.content.Context
import androidx.core.content.edit

private const val SHARE_AUTH = "share_auth"

private const val KEY_ACCESS_TOKEN = "api_access_token"
private const val KEY_REFRESH_TOKEN = "api_refresh_token"
private const val KEY_API_KEY = "api_key"

class CacheStore {
    private lateinit var context: Context

    constructor(context: Context) {
        this.context = context
    }

    private fun saveValue(key: String, value: String) {
        val perf = context.getSharedPreferences(SHARE_AUTH, Context.MODE_PRIVATE)
        perf.edit {
            putString(key, value)
        }
    }

    private fun loadValue(key: String): String? {
        val perf = context.getSharedPreferences(SHARE_AUTH, Context.MODE_PRIVATE)
        return perf.getString(key, null)
        return null
    }

    fun clearValue() {
        val perf = context.getSharedPreferences(SHARE_AUTH, Context.MODE_PRIVATE)
        perf.edit {
            clear()
        }
    }

    fun saveApiKey(apiKey: String) {
        saveValue(KEY_API_KEY, apiKey)
    }
    fun loadApiKey(): String? {
        return loadValue(KEY_API_KEY)
    }

    fun saveAccessToken(accessToken: String) {
        saveValue(KEY_ACCESS_TOKEN, accessToken)
    }
    fun loadAccessToken(): String? {
        return loadValue(KEY_ACCESS_TOKEN)
    }

    fun saveRefreshToken(refreshToken: String) {
        saveValue(KEY_REFRESH_TOKEN, refreshToken)
    }
    fun loadRefreshToken(): String? {
        return loadValue(KEY_REFRESH_TOKEN)
    }
}