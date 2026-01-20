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
    private val _state = MutableStateFlow(MainState())//локальний, який можемо змінювати
    val state: StateFlow<MainState> = _state.asStateFlow()//для screen(глобальний), не годні міняти

    //викличеться при створенні обєкту класу VM
    init {
        loadNextPage()
    }

    fun loadNextPage() {
        val currentState = _state.value
        //якшо вже грузимо або вже всьо показали шо було на сервері і нема куда - виходимо з функції
        if (currentState.isLoading || currentState.end) return

        //запускаємо корутину і оновлюємо state
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            try {
                // Просимо сторінку, яка записана у нас в State
                val newMovies = getPopularMoviesUseCase(currentState.page)
                _state.update {
                    it.copy(
                        isLoading = false,
                        // Якщо список прийшов порожній -> значить це кінець (endReached = true)
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