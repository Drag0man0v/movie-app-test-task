package com.example.movie_app.data.remote.api

import com.example.movie_app.data.remote.dto.MovieDto
import com.example.movie_app.data.remote.dto.MoviesResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    //for getting the list of movies
    //todo: maybe create another DTO for displaying this (with fewer fields)
    @GET("discover/movie")
    suspend fun getMovies(
    @Query("page") page: Int = 1,
    @Query("language") language: String = "uk-UA"
    ): MoviesResponseDto

    //movie details
    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") id: Int,//replaces into the path
        @Query("language") language: String = "uk-Ua"
        ): MovieDto
}