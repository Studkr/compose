package com.cinema.ui.view.preview

import androidx.compose.runtime.Immutable
import com.cinema.ui.view.details.DetailsContract
import com.cinema.utils.mvi.UiEffect
import com.cinema.utils.mvi.UiEvent
import com.cinema.utils.mvi.UiState
import com.example.weather.data.model.MovieDetailsResponceModel

class PreviewContract {
    @Immutable
    sealed class PreviewScreenEvent : UiEvent {
        object IDLE : PreviewScreenEvent()
        object Loading : PreviewScreenEvent()
        object Success : PreviewScreenEvent()
    }

    @Immutable
    sealed class PreviewScreenState : UiState {
        object Loading : PreviewScreenState()
        object Finish : PreviewScreenState()
    }

    sealed class Effect : UiEffect {
        object ShowToast : Effect()
    }

}