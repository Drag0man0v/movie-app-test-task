package com.example.movie_app.presentation.screens.main

import com.example.movie_app.domain.models.MovieModel

data class MainState(
    val isLoading: Boolean = false,
    val movies: List<MovieModel> = emptyList(),
    val error: String? = null,
    val page: Int = 1,
    val end: Boolean = false
)