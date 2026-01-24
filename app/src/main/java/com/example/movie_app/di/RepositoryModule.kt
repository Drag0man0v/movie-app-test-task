package com.example.movie_app.di

import com.example.movie_app.data.repositoryImpl.MovieRepositoryImpl
import com.example.movie_app.domain.repositoryIntf.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//bind the MovieRepository interface to its implementation MovieRepositoryImpl -> allows injecting movieRepository into a useCase
@Suppress("unused")//make warning ignored todo: check why IDE thinks this class is unused
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindMovieRepository(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository
}