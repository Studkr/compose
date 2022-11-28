package com.cinema.ui.view.home

import android.util.Log
import com.cinema.data.SerialsTrendingUseCase
import com.cinema.data.TrendingUseCase
import com.cinema.utils.Status
import com.cinema.utils.base.BaseViewModel
import com.cinema.repository.MediaModel
import com.cinema.repository.enum.ResponseType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val trendingUseCase: TrendingUseCase,
    private val serialsTrendingUseCase: SerialsTrendingUseCase
) : BaseViewModel<HomeContract.HomeScreenEvent, HomeContract.State>() {

    private val movieList: MutableStateFlow<List<MediaModel>> = MutableStateFlow(emptyList())
    private val serialsList: MutableStateFlow<List<MediaModel>> = MutableStateFlow(emptyList())

    init {
        setEvent(HomeContract.HomeScreenEvent.IDLE)
        launch {
            loadMovieList()
            loadSerialsList()
        }
    }

    override fun createInitialState(): HomeContract.State =
        HomeContract.State(HomeContract.HomeScreenState.Idle)

    override fun handleEvent(event: HomeContract.HomeScreenEvent) {
        when (event) {
            is HomeContract.HomeScreenEvent.IDLE -> {
                setLoadDataState()
            }
            is HomeContract.HomeScreenEvent.Loading -> {
                if (movieList.value.isNotEmpty()) {
                    setState {
                        currentState.copy(
                            homeScreenState = HomeContract.HomeScreenState.ShowMovieList(
                                movieList.value
                            )
                        )
                    }
                }
            }
            is HomeContract.HomeScreenEvent.SerialsTabClicked -> {
                setState {
                    currentState.copy(
                        homeScreenState = HomeContract.HomeScreenState.ShowSerialsList(
                            serialsList.value
                        )
                    )
                }
            }
            is HomeContract.HomeScreenEvent.MovieTabClicked -> {
                setState {
                    currentState.copy(
                        homeScreenState = HomeContract.HomeScreenState.ShowMovieList(
                            movieList.value
                        )
                    )
                }

            }
            is HomeContract.HomeScreenEvent.OpenScreenDetails -> {

            }
            else -> {

            }
        }
    }

    private fun loadSerialsList() {
        launch {
            makeSerialsResponse()
        }
    }

    private fun makeSerialsResponse() {
        launch {
            serialsTrendingUseCase.invoke().collect {
                when (it.status) {
                    Status.SUCCESS -> {
                        serialsList.value = it.data!!
                    }
                    Status.ERROR -> {

                    }
                    Status.LOADING -> {

                    }
                }
            }
        }
    }

    private fun loadMovieList() {
        launch {
            if (movieList.value.isEmpty()) {
                makeMovieResponse()
            }
        }
    }

    private suspend fun makeMovieResponse() {
        trendingUseCase.invoke().collect {
            when (it.status) {
                Status.SUCCESS -> {
                    movieList.value = it.data!!
                    setState {
                        currentState.copy(
                            homeScreenState = HomeContract.HomeScreenState.ShowMovieList(
                                movieList.value
                            )
                        )
                    }
                }
                Status.ERROR -> {

                }
                Status.LOADING -> {

                }
            }
        }
    }

    private fun setLoadDataState() {
        setEvent(HomeContract.HomeScreenEvent.Loading)
    }

    fun mediaItemClicked(model: MediaModel) {
        when (model.type) {
            ResponseType.MOVIE -> {
                val movieId = model.id
                setEvent(HomeContract.HomeScreenEvent.OpenScreenDetails(movieId))
            }
            ResponseType.SERIALS -> {

            }
        }

    }

    fun setSerialsEvent() {
        setEvent(HomeContract.HomeScreenEvent.SerialsTabClicked)
    }

    fun setMovieTabEvent() {
        setEvent(HomeContract.HomeScreenEvent.MovieTabClicked)
    }

    fun stopNavigationEvent() {
        //  setEvent(HomeContract.HomeScreenEvent.StopNavigation(false))
    }
}