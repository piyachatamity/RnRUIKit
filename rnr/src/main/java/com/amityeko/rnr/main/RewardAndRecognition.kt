package com.amityeko.rnr.main

import android.content.Context
import com.amityeko.rnr.usecase.RegisterDeviceUseCase
import io.reactivex.rxjava3.core.Flowable

class RewardAndRecognition {
    companion object {
        fun registerDevice(context: Context, apiKey: String, userId: String, userName: String): Flowable<Boolean> {
            return RegisterDeviceUseCase(context).execute(apiKey, userName, userId)
        }
    }
}