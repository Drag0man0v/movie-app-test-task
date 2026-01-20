package com.example.movie_app.data.repositoryImpl

import com.example.movie_app.data.mappers.mapMovie
import com.example.movie_app.data.remote.api.MovieApi
import com.example.movie_app.domain.models.MovieModel
import com.example.movie_app.domain.repositoryIntf.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val api: MovieApi): MovieRepository
{
    override suspend fun getMovies(page: Int): List<MovieModel> {
        val resultOfRequest = api.getMovies(page)//наш респонз у форматі MoviesResponseDto
        val listOfDto = resultOfRequest.results//вибираємо з нього тільки інформацію про наші фільми
        val result = listOfDto.map{mapMovie(it)}//перетворюємо з dto у клас Domain рівня з яким вже і будемо працювати
        return result
    }

    override suspend fun getMovieDetail(id: Int): MovieModel {
    val movieDto = api.getMovieDetail(id)
    return mapMovie(movieDto)
    }

}