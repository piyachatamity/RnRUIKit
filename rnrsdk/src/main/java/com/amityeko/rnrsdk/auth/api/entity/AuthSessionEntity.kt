package com.amityeko.rnrsdk.auth.api.entity

data class AuthSessionEntity (
    val accessToken: String,
    val refreshToken: String,
)