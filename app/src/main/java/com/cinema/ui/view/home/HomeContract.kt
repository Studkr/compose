package com.cinema.ui.view.home

import androidx.compose.runtime.Immutable
import com.cinema.utils.mvi.UiEffect
import com.cinema.utils.mvi.UiEvent
import com.cinema.utils.mvi.UiState
import com.cinema.repository.MediaModel

class HomeContract {

    //event - when user click on item or other action
    @Immutable
    sealed class HomeScreenEvent : UiEvent {
        object IDLE : HomeScreenEvent()
        object Loading : HomeScreenEvent()
        class OpenScreenDetails(val movieId: Long) : HomeScreenEvent()
        object MovieTabClicked : HomeScreenEvent()
        object SerialsTabClicked : HomeScreenEvent()
    }

    data class State(
        val homeScreenState: HomeScreenState
    ) : UiState


    // screen state
    @Immutable
    sealed class HomeScreenState {
        object Loading : HomeScreenState()
        object Idle : HomeScreenState()
        data class ShowMovieList(val mediaModel:List<MediaModel>):HomeScreenState()
        data class ShowSerialsList(val mediaModel: List<MediaModel>):HomeScreenState()
    }

    // effect - side effect action should be use one time
    sealed class Effect : UiEffect {
        object ShowToast : Effect()
    }
}