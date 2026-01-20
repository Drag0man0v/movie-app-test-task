package com.example.movie_app.presentation.screens.details

import com.example.movie_app.domain.models.MovieModel

data class DetailsState(
    val isLoading: Boolean = false,
    val movie: MovieModel? = null,
    val error: String? = null
)
