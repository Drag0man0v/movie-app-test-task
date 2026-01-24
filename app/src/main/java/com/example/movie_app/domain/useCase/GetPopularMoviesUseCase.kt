package com.example.movie_app.domain.useCase

import com.example.movie_app.domain.models.MovieModel
import com.example.movie_app.domain.repositoryIntf.MovieRepository
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(private val repository: MovieRepository) {
    //"operator fun invoke" allows calling the class instance like a function
    suspend operator fun invoke(page: Int = 1): List<MovieModel> {
        return repository.getMovies(page)
    }
}