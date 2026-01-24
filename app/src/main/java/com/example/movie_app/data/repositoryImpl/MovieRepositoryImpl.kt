package com.example.movie_app.data.repositoryImpl

import com.example.movie_app.data.mappers.mapMovie
import com.example.movie_app.data.remote.api.MovieApi
import com.example.movie_app.domain.models.MovieModel
import com.example.movie_app.domain.repositoryIntf.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val api: MovieApi): MovieRepository
{
    override suspend fun getMovies(page: Int): List<MovieModel> {
        val resultOfRequest = api.getMovies(page) //our response in the format MoviesResponseDto
        val listOfDto = resultOfRequest.results //select only the information about our movies
        val result = listOfDto.map { mapMovie(it) } //convert from DTO to Domain class that we will work with
        return result
    }

    override suspend fun getMovieDetail(id: Int): MovieModel {
    val movieDto = api.getMovieDetail(id)
    return mapMovie(movieDto)
    }

}