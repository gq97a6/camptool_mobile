package com.campbuddy.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface Login {
    @POST("/login")
    suspend fun login(@Body data: Map<String, String>): Response<Unit?>
}