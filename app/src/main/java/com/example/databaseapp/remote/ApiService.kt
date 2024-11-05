package com.example.databaseapp.remote

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("public")
    suspend fun getProfileImage(): Response<String>
}