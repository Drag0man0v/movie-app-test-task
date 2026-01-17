package com.example.movie_app.data.remote.api

import com.example.movie_app.BuildConfig
import com.example.movie_app.data.remote.dto.MoviesResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("discover/movie")
    suspend fun getMovies(
    @Query("page") page: Int = 1,
    @Query("language") language: String = "uk-UA"): MoviesResponseDto

}