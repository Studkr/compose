package com.cinema.ui.view.details

import android.util.Log
import com.cinema.data.ShowDetailsUseCase
import com.cinema.utils.Result
import com.cinema.utils.Status
import com.cinema.utils.base.BaseViewModel
import com.example.weather.data.model.MovieDetailsResponceModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val showDetailsUseCase: ShowDetailsUseCase
) : BaseViewModel<DetailsContract.DetailsScreenEvent, DetailsContract.State>() {

    private var showId: Long = 0L


    init {
        setEvent(DetailsContract.DetailsScreenEvent.IDLE)
    }

    val data = MutableStateFlow<Result<MovieDetailsResponceModel?>>(Result.loading(null))

    override fun createInitialState(): DetailsContract.State =
        DetailsContract.State(DetailsContract.DetailsScreenState.Loading)


    override fun handleEvent(event: DetailsContract.DetailsScreenEvent) {
        when (event) {
            is DetailsContract.DetailsScreenEvent.Loading -> {
                getShowDetails()
            }
            is DetailsContract.DetailsScreenEvent.IDLE -> {

            }
            is DetailsContract.DetailsScreenEvent.Success ->{
                
            }
            else -> {}
        }
    }

    fun setShowId(id:Long){
        showId = id
        setEvent(DetailsContract.DetailsScreenEvent.Loading)
    }


    private fun getShowDetails() {
        launch {
            showDetailsUseCase.invoke(showId).collect {
                data.emit(it)
            }
        }
    }
}