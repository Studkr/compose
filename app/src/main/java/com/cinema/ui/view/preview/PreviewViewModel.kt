package com.cinema.ui.view.preview

import android.util.Log
import com.cinema.utils.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PreviewViewModel @Inject constructor(

) : BaseViewModel<PreviewContract.PreviewScreenEvent, PreviewContract.PreviewScreenState>() {

    override fun createInitialState(): PreviewContract.PreviewScreenState = PreviewContract.PreviewScreenState.Loading

    init {
        changeScreen()
    }

    private fun changeScreen(){
        launch {
            delay(3000)
            Log.d("BAA","finish")
            setState {
               PreviewContract.PreviewScreenState.Finish
            }
        }
    }

    override fun handleEvent(event: PreviewContract.PreviewScreenEvent) {
        when (event) {
            PreviewContract.PreviewScreenEvent.IDLE -> {

            }
            PreviewContract.PreviewScreenEvent.Loading -> {

            }
            PreviewContract.PreviewScreenEvent.Success -> {

            }
        }
    }

}