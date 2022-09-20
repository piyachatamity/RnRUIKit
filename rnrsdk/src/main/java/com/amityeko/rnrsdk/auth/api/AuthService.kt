package com.amityeko.rnrsdk.auth.api

import com.amityeko.rnrsdk.auth.api.dto.auth_session.AuthSessionDto
import com.amityeko.rnrsdk.auth.api.entity.AuthSessionEntity
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {
    @POST("sessions")
    suspend fun authSession(@Header("x-api-key") apiKey: String, @Body params: AuthSessionDto): Response<AuthSessionEntity>
}