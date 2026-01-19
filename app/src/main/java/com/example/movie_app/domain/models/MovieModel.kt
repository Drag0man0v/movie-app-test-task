package com.example.movie_app.domain.models

data class MovieModel(
    val id: Int,
    val title: String,
    val overview: String,
    val imageUrl: String?,
    val date: String,
    val rating: Double
)
