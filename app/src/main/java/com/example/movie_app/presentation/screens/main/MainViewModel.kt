package com.example.movie_app.presentation.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie_app.domain.useCase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel(){
    private val _state = MutableStateFlow(MainState())//local state that we can modify
    val state: StateFlow<MainState> = _state.asStateFlow()//for the screen (global), cannot modify

    //called when the ViewModel object is created
    init {
        loadNextPage()
    }

    fun loadNextPage() {
        val currentState = _state.value

        //if already loading or reached the end of server data, exit function
        if (currentState.isLoading || currentState.end) return

        //launch a coroutine and update the state
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            try {
                //request the page stored in the state
                val newMovies = getPopularMoviesUseCase(currentState.page)
                _state.update {
                    it.copy(
                        isLoading = false,
                        //if the list is empty -> this is the end (end = true)
                        end = newMovies.isEmpty(),
                        movies = it.movies + newMovies,
                        page = it.page + 1
                    )
                }
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, error = e.message) }
            }

        }

    }
}