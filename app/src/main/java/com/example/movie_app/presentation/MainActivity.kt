package com.example.movie_app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movie_app.presentation.screens.details.DetailsScreen
import com.example.movie_app.presentation.screens.main.MainScreen
import com.example.movie_app.presentation.theme.Movie_appTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            //navigation controller
            val navController = rememberNavController()

            //theme state
            val isDarkTheme = remember { mutableStateOf(true) }

            Movie_appTheme(darkTheme = isDarkTheme.value) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //set up navigation routes (NavHost))
                    NavHost(
                        navController = navController,
                        startDestination = "movie_list" //starting screen
                    ) {

                        //first screen
                        composable(route = "movie_list") {
                            MainScreen(
                                isDarkTheme = isDarkTheme.value,
                                onThemeChange = {
                                    isDarkTheme.value = !isDarkTheme.value
                                },
                                //click for navigation
                                onNavigate = { movieId ->
                                    navController.navigate("details/$movieId")
                                }
                            )
                        }

                        //second screen
                        composable(route = "details/{movieId}",
                            arguments = listOf(
                                navArgument("movieId") { type = NavType.StringType }
                            )
                        ) {
                            DetailsScreen(
                                onBackClick = {
                                    navController.popBackStack() //return to previous screen
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}