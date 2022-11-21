package com.cinema.ui.view

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cinema.compose.RoundedProgressBar
import com.cinema.navigation.NavRouters
import com.cinema.ui.theme.CinemaTheme
import com.cinema.ui.view.details.DetailsViewModel
import com.cinema.ui.view.details.ShowDetails
import com.cinema.ui.view.home.HomeScreen
import com.cinema.ui.view.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CinemaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavRouters.Home.route) {
        composable(NavRouters.Home.route) {
            val viewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(
                viewModel = viewModel,
                navController = navController
            )
        }
        composable(
            route = NavRouters.Details.route + "/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.LongType })
        ) { backStack ->
            val movieId = backStack.arguments?.getLong("movieId")
            val viewModel = hiltViewModel<DetailsViewModel>()
            requireNotNull(movieId) { "movie id null" }
            ShowDetails(viewModel = viewModel, navController = navController, movieId = movieId)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CinemaTheme {
        RoundedProgressBar()
    }
}

