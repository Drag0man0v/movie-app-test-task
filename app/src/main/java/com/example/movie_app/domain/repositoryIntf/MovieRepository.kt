package com.example.movie_app.domain.repositoryIntf

import com.example.movie_app.domain.models.MovieModel

interface MovieRepository {
    suspend fun getMovies(page: Int): List<MovieModel>
    suspend fun getMovieDetail(id: Int): MovieModel
}