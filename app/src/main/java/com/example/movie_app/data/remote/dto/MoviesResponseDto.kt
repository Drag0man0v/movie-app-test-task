package com.example.movie_app.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//як респонз нам повертається обєкт, який містить поля і список result, у якому якраз є об'єкти MovieDto
@Serializable
data class MoviesResponseDto(
    @SerialName("page") val page: Int,
    @SerialName("results") val results: List<MovieDto>
)
