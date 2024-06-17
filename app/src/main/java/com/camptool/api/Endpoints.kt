package com.camptool.api

import com.camptool.classes.Day
import com.camptool.classes.Kid
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Endpoints {
    @POST("/login")
    suspend fun login(@Body data: Map<String, String>): Response<Unit?>

    @GET("/kid/{uuid}")
    suspend fun getKid(@Path("uuid") uuid:String): Response<Kid>

    @GET("/kid")
    suspend fun getAllKids(): Response<List<Kid>>

    @POST("/kid/{uuid}")
    suspend fun upsertKid(@Body kid: Kid, @Path("uuid") uuid:String): Response<Unit?>

    @GET("/day/{uuid}")
    suspend fun getDay(@Path("uuid") uuid:String): Response<Day>

    @GET("/day")
    suspend fun getAllDays(): Response<List<Day>>

    @POST("/day/{uuid}")
    suspend fun upsertDay(@Body day: Day, @Path("uuid") uuid:String): Response<Unit?>
}