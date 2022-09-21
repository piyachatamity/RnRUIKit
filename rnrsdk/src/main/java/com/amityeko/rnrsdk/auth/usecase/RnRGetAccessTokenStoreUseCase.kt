package com.amityeko.rnrsdk.auth.usecase

import android.content.Context
import com.amityeko.rnrsdk.auth.repository.AuthRepository

class RnRGetAccessTokenStoreUseCase {
    private val context: Context
    private val authRepository: AuthRepository

    constructor(context: Context) {
        this.context = context
        this.authRepository = AuthRepository(context)
    }

    fun execute(): String {
        val tokenModel = authRepository.loadToken()
        if (tokenModel == null) {
            return ""
        } else {
            return tokenModel.accessToken
        }
    }
}