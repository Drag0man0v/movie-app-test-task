package com.example.movie_app.presentation.screens.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie_app.domain.useCase.GetMoviesDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMoviesDetailsUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(DetailsState())
    val state: State<DetailsState> = _state

    init {
        val movieIdString = savedStateHandle.get<String>("movieId")
        //якшо не нуль то викликажмо сторінку
        if (movieIdString != null) {
            getMovie(movieIdString.toInt())
        }
        else{
            _state.value = _state.value.copy(
                error = "Щось пішло не так"
            )
        }
    }

    private fun getMovie(id: Int) {
        viewModelScope.launch {
            try {
                _state.value = _state.value.copy(
                    isLoading = true,
                    error = null
                )
                val movie = getMovieDetailUseCase(id)
                _state.value = _state.value.copy(
                    movie = movie,
                    isLoading = false
                )

            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message ?: "Щось пішло не так"
                )
            }
        }
    }
}