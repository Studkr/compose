package com.cinema.ui.view.home

import androidx.compose.runtime.Immutable
import com.cinema.utils.mvi.UiEffect
import com.cinema.utils.mvi.UiEvent
import com.cinema.utils.mvi.UiState
import com.cinema.repository.MediaModel

class HomeContract {
    @Immutable
    sealed class HomeScreenEvent : UiEvent {
        object IDLE : HomeScreenEvent()
        object Loading : HomeScreenEvent()
        class Success(val data: List<MediaModel>) : HomeScreenEvent()
        class OpenScreenDetails(val movieId: Long) : HomeScreenEvent()
        object FinishState : HomeScreenEvent()
        object MovieTabClicked : HomeScreenEvent()
        object SerialsTabClicked : HomeScreenEvent()
    }

    data class State(
        val homeScreenState: HomeScreenState
    ) : UiState

    @Immutable
    sealed class HomeScreenState {
        object Loading : HomeScreenState()
        object Idle : HomeScreenState()
        data class SuccessMovie(val data: List<MediaModel>) : HomeScreenState()
        data class SuccessSerials(val data: List<MediaModel>) : HomeScreenState()
    }

    sealed class Effect : UiEffect {
        object ShowToast : Effect()
    }
}