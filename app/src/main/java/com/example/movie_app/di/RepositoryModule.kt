package com.example.movie_app.di

import com.example.movie_app.data.repositoryImpl.MovieRepositoryImpl
import com.example.movie_app.domain.repositoryIntf.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//зв’язуємо інтерфейс MovieRepository з його реалізацією MovieRepositoryImpl -> можна інжектити movieRepository у useCase
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindMovieRepository(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository
}