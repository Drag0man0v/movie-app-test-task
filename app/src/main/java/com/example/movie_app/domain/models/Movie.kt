package com.example.movie_app.domain.models

import kotlinx.serialization.SerialName

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val imageUrl: String,
    val date: String,
    val rating: Double
)
