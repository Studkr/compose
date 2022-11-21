package com.cinema.ui.view.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.cinema.compose.RoundedProgressBar
import com.cinema.compose.TextMiddle
import com.cinema.compose.TextTitle
import com.cinema.navigation.NavRouters
import com.cinema.sealed.HomeTabs
import com.cinema.repository.MediaModel
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    navController: NavController
) {
    val state by viewModel.uiState.collectAsState()
    val event by viewModel.event.collectAsState(initial = HomeContract.HomeScreenEvent.IDLE)

    when (event) {
        is HomeContract.HomeScreenEvent.OpenScreenDetails -> {
            val movieId = (event as HomeContract.HomeScreenEvent.OpenScreenDetails).movieId
            navController.navigate(NavRouters.Details.route + "/$movieId") {
                launchSingleTop = true
            }
        }
        is HomeContract.HomeScreenEvent.IDLE -> {
            RoundedProgressBar()
        }
        else -> {}
    }
    Box {
        Scaffold(
            backgroundColor = Color.Black
        ) { v ->
            Column(modifier = Modifier.padding(v)) {
                CombinedTab(viewModel, state = state)
            }
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun CombinedTab(viewModel: HomeViewModel, state: HomeContract.State) {
    val tabList = HomeTabs.returnList()
    val pagerState = rememberPagerState(
        initialPage = 0,
    )
    val tabIndex = pagerState.currentPage
    val coroutineScope = rememberCoroutineScope()
    Column {
        TabRow(
            selectedTabIndex = tabIndex,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                )
            }
        ) {
            tabList.mapIndexed { index, pair ->
                Tab(selected = tabIndex == index, onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }, text = {
                    Text(text = pair.tabName)
                }
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f),
            count = tabList.size
        ) { currentPage ->

            if (tabIndex == 0) {
                viewModel.setMovieTabEvent()
            } else {
                viewModel.setSerialsEvent()
            }

            when (state.homeScreenState) {
                is HomeContract.HomeScreenState.SuccessMovie -> {
                    MovieList(list = (state.homeScreenState as HomeContract.HomeScreenState.SuccessMovie).data)
                }
                is HomeContract.HomeScreenState.SuccessSerials -> {
                    MovieList(list = (state.homeScreenState as HomeContract.HomeScreenState.SuccessSerials).data)
                }
                else -> {}
            }
        }
    }
}


@Composable
fun MovieList(list: List<MediaModel>) {
    val rememberList = remember {
        mutableStateOf(list)
    }
    LazyColumn {
        itemsIndexed(rememberList.value) { _, item ->
            ListItem(model = item)
        }

    }
}

@Composable
fun ListItem(
    model: MediaModel,
    viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(12.dp)
            .wrapContentHeight()
            .fillMaxWidth()
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null,
                onClick = {
                    viewModel.mediaItemClicked(model)
                }
            ),
        elevation = 8.dp,
        backgroundColor = Color.Gray
    ) {
        Box {
            Column(modifier = Modifier.padding(bottom = 4.dp)) {
                Row {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w500/${model.poster}",
                        contentDescription = ""
                    )
                    Column(modifier = Modifier.padding(8.dp)) {
                        TextTitle(text = model.name)
                        TextMiddle(text = model.releaseDate)
                    }
                }
                TextMiddle(
                    text = model.overview,
                    bottom = 10.dp,
                    start = 8.dp,
                    end = 8.dp,
                    top = 8.dp
                )
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Preview(showBackground = true)
@Composable
fun previewHome() {
    val pagerState = rememberPagerState()
    //  HomeScreenTabs(HomeTabs.returnList(), pagerState)
}