package com.example.tapassessment.network

import com.example.tapassessment.model.NewMovie
import retrofit2.Response
import retrofit2.http.GET

interface NetworkService {
    @GET("popular")
    suspend fun getPopularMovies():NewMovie
}