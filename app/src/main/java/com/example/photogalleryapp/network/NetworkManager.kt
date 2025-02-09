package com.example.photogalleryapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkManager {
    private const val BASE_URL = "https://api.unsplash.com/"
    const val API_KEY = "02gGe-udsrIxdKqs9eS24lfhdZ0nkMUG3dLhlqBMcrk" // 🔥 Замени на свой API-ключ

    val api: UnsplashApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UnsplashApi::class.java)
    }
}
