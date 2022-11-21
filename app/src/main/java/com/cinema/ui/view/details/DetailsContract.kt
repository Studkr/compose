package com.cinema.ui.view.details

import androidx.compose.runtime.Immutable
import com.cinema.utils.mvi.UiEffect
import com.cinema.utils.mvi.UiEvent
import com.cinema.utils.mvi.UiState
import com.example.weather.data.model.MovieDetailsResponceModel

class DetailsContract {
    @Immutable
    sealed class DetailsScreenEvent : UiEvent {
        object IDLE : DetailsScreenEvent()
        object Loading : DetailsScreenEvent()
        object Success : DetailsScreenEvent()
    }

    data class State(
        val detailsScreenState: DetailsScreenState
    ) : UiState

    @Immutable
    sealed class DetailsScreenState{
        object Loading: DetailsScreenState()
        object Idle:DetailsScreenEvent()
        data class Success(val data:MovieDetailsResponceModel):DetailsScreenEvent()
    }

    sealed class Effect : UiEffect {
        object ShowToast : Effect()
    }

}