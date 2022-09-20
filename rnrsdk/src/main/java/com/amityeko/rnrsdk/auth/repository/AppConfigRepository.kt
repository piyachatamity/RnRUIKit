package com.amityeko.rnrsdk.auth.repository

import android.content.Context
import com.amityeko.common.cache_store.CacheStore

class AppConfigRepository {
    private val cacheStore: CacheStore

    constructor(context: Context) {
        cacheStore = CacheStore(context)
    }

    fun saveApiKey(apiKey: String) {
        cacheStore.saveApiKey(apiKey)
    }

    fun loadApiKey(): String {
        return cacheStore.loadApiKey() ?: ""
    }

    fun clearApiKey() {
        cacheStore.clearValue()
    }
}