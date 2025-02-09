package com.example.photogalleryapp.network

import com.example.photogalleryapp.model.UnsplashImage
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApi {
    @GET("photos/random")
    suspend fun fetchImages(
        @Query("count") count: Int = 30,
        @Query("client_id") apiKey: String = NetworkManager.API_KEY // 🔥 Берем API-ключ отсюда
    ): List<UnsplashImage>
}
