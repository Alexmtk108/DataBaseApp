package com.example.databaseapp.remote

import okhttp3.OkHttpClient
import  okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val okHttpLoggingInterceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}
val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(okHttpLoggingInterceptor)
    .build()

val retrofit = Retrofit.Builder()
    .baseUrl("https://api.unsplash.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .client(okHttpClient)
    .build()
