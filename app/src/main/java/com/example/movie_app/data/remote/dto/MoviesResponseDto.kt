package com.example.movie_app.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// the response returns an object containing fields and a list called result, which contains MovieDto objects
@Serializable
data class MoviesResponseDto(
    @SerialName("page") val page: Int,
    @SerialName("results") val results: List<MovieDto>
)
