package com.example.movie_app.data.remote.api

import com.example.movie_app.BuildConfig
import com.example.movie_app.data.remote.dto.MovieDto
import com.example.movie_app.data.remote.dto.MoviesResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    //для отримання списку фільмів
    //todo можливо треба доробити ше один dto для відображення того всього(з меншою кількістю полів)
    @GET("discover/movie")
    suspend fun getMovies(
    @Query("page") page: Int = 1,
    @Query("language") language: String = "uk-UA"
    ): MoviesResponseDto

    //деталі 1 фільму
    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") id: Int,//підставляє в шлях
        @Query("language") language: String = "uk-Ua"
        ): MovieDto
}