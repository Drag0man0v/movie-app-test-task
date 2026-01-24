package com.example.movie_app.di

import com.example.movie_app.BuildConfig
import com.example.movie_app.data.remote.api.MovieApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides//when someone needs OkHttpClient, this function will be called
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        //intercept each API request and add headers
        val interceptor = Interceptor { chain -> //chain - current request
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer ${BuildConfig.API_KEY}")
                .addHeader("accept", "application/json")
                .build()//creates a new request
            chain.proceed(request)//pass the request further (to next interceptors or execute the request)
        }
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieApi(client: OkHttpClient): MovieApi {
        val json = Json { ignoreUnknownKeys = true }//JSON configuration
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")//all requests start with this URL
            .client(client)//sets the instance that will execute the requests
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(MovieApi::class.java)//generates implementation of MovieApi interface
    }
}