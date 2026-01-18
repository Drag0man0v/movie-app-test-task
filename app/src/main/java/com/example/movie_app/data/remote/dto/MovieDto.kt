package com.example.movie_app.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MovieDto(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("overview") val overview: String,
    @SerialName("poster_path") val imagePath: String,
    @SerialName("release_date") val date: String,
    @SerialName("vote_average") val rating: Double
    //todo як буде час, то треба додати ше поле типу List із жанрами
)
