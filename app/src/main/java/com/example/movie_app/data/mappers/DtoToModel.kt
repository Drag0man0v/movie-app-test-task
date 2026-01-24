package com.example.movie_app.data.mappers

import com.example.movie_app.data.remote.dto.MovieDto
import com.example.movie_app.domain.models.MovieModel

fun mapMovie(dto: MovieDto): MovieModel{
    return MovieModel(
        id = dto.id,
        title = dto.title,
        overview = dto.overview,
        imageUrl = "https://image.tmdb.org/t/p/w500${dto.imagePath?: ""}",
        bigImageUrl = "https://image.tmdb.org/t/p/w500${dto.bigImagePath?: ""}",
        //todo: improve logic to also include day and month
        date = dto.date.take(4),
        rating = dto.rating
    )
}
