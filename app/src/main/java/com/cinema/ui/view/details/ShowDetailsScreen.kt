package com.cinema.ui.view.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.cinema.R
import com.cinema.compose.*
import com.cinema.utils.Status
import com.example.weather.data.model.Genre
import com.example.weather.data.model.MovieDetailsResponceModel


@Composable
fun ShowDetails(
    viewModel: DetailsViewModel,
    navController: NavController,
    movieId: Long
) {
    viewModel.setShowId(movieId)
    val data by viewModel.data.collectAsState()

    when (data.status) {
        Status.SUCCESS -> {
            ShowDetailsView(model = data.data!!, navController = navController)
        }
        Status.ERROR -> {

        }
        Status.LOADING -> {
            RoundedProgressBar()
        }
    }
}

@Composable
fun ShowDetailsView(model: MovieDetailsResponceModel, navController: NavController) {
    Box() {
        Column {
            Scaffold(
                topBar = {
                    ToolBar(navController)
                },
                backgroundColor = Color.Black,
            ) { paddingValues ->

                Column(
                    Modifier
                        .padding(paddingValues)
                        .verticalScroll(rememberScrollState())
                        .fillMaxSize()
                ) {
                    Row {
                        AsyncImage(
                            model = "https://image.tmdb.org/t/p/w500/${model.posterPath}",
                            contentDescription = "poster image",
                            modifier = Modifier
                                .clip(RoundedCornerShape(16.dp))
                        )
                    }
                    Column() {
                        TextTitle(text = model.title)
                        TextMiddle(text = model.tagline)
                        TextMiddle(text = "Status: ${model.status}")
                        TextMiddle(text = "Release Date: ${model.releaseDate}")
                        TextMiddle(text = "${stringResource(id = R.string.budget)}: ${model.budget}")
                        TextMiddle(text = stringResource(id = R.string.description))
                        TextMiddle(text = model.overview)
                    }
                    ActorView(list = model.genres)
                }
            }

        }

    }
}


@Composable
fun ActorView(list: List<Genre>) {
    LazyRow {
        itemsIndexed(list) { _, item ->
            GenreItem(item)
        }
    }
}


@Composable
fun GenreItem(item: Genre) {
    Card(modifier = Modifier.padding(8.dp), shape = RoundedCornerShape(16.dp), elevation = 8.dp) {
        Box {
          TextMiddleWithPadding(text = item.name)
        }
    }
}


@Composable
fun ToolBar(navController: NavController) {
    TopAppBar(
        backgroundColor = Color.Black
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_baseline_arrow_back_ios_new_24),
            contentDescription = "",
            modifier = Modifier.clickable {
                navController.popBackStack()
            },
        )
        Text(
            text = stringResource(id = R.string.show_details),
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}
