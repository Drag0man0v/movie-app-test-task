package com.example.movie_app.presentation.screens.main

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movie_app.presentation.common.MovieItem

@OptIn(ExperimentalMaterial3Api::class)//topAppBar is still experimental
@Composable
fun MainScreen(
    //Hilt will automatically find and create the ViewModel
    viewModel: MainViewModel = hiltViewModel(),
    isDarkTheme: Boolean,
    onThemeChange: () -> Unit,
    onNavigate: (Int) -> Unit
) {
    //subscribe to stateFlow -> changes in VM automatically update the UI
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title ={},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                actions = {
                    IconButton(onClick = onThemeChange) {
                        val icon = if (isDarkTheme) Icons.Rounded.DarkMode else Icons.Rounded.LightMode
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            )

        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
        ) {

            //list of movies
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp), //padding from cards to edges
                verticalArrangement = Arrangement.spacedBy(16.dp) //spacing between cards
            ){
                itemsIndexed(state.movies) { index, item ->
                    //if reached the second to last item -> load the next page
                    if (index >= state.movies.lastIndex - 1 && !state.end && !state.isLoading) {
                        viewModel.loadNextPage()
                    }

                    MovieItem(
                        movie = item,
                        onMovieClick = { movieId -> onNavigate(movieId) }
                    )
                }

                //loading spinner that appears when a request is in progress and it's not the first load
                item {
                    if (state.isLoading && state.movies.isNotEmpty()) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary)
                        }
                    }
                }
            }

            //shows loading indicator on the first launch
            if (state.isLoading && state.movies.isEmpty()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            //error
            if (state.error != null) {
                val errorMessage = state.error!!

                //log the error in console (runs once)
                LaunchedEffect(errorMessage) {
                    Log.e("MainScreenError", "Сталася помилка: $errorMessage")
                }

                //bottom screen banner
                Surface(
                    color = MaterialTheme.colorScheme.onPrimary,
                    contentColor = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(23.dp),
                    shadowElevation = 4.dp,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Warning,
                            contentDescription = null,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Text(
                            text = errorMessage,
                            style = MaterialTheme.typography.bodyMedium,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}
