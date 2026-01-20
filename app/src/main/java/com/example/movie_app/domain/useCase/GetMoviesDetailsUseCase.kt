package com.example.movie_app.domain.useCase

import com.example.movie_app.domain.models.MovieModel
import com.example.movie_app.domain.repositoryIntf.MovieRepository
import javax.inject.Inject

class GetMoviesDetailsUseCase @Inject constructor( private val repository: MovieRepository) {
    suspend operator fun invoke(id: Int): MovieModel {
        return repository.getMovieDetail(id)
    }
}