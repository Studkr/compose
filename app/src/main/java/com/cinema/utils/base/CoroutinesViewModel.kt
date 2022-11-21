package com.cinema.utils.base

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlin.coroutines.CoroutineContext

open class CoroutinesViewModel : ViewModel(), CoroutineScope {

    private val errorHandler = CoroutineExceptionHandler { _, error ->
        error.printStackTrace()
        launch { _errorEvents.emit(error) }
    }

    override val coroutineContext: CoroutineContext =
        SupervisorJob() + Dispatchers.Main.immediate + errorHandler

    val ioScope = CoroutineScope(
        SupervisorJob() + Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            Log.e(CoroutinesViewModel::class.java.simpleName, "ioScope ${throwable.message}")
            throwable.printStackTrace()
        }
    )

    private val _errorEvents: MutableSharedFlow<Throwable> = MutableSharedFlow()
    val errorEvents: SharedFlow<Throwable>
        get() = _errorEvents
}