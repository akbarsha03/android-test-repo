package com.akbarsha03.colearn.feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    internal val tag = this.javaClass.simpleName

    internal inline fun ViewModel.launchUseCase(
        handler: CoroutineExceptionHandler,
        crossinline block: suspend () -> Unit
    ): Job = viewModelScope.launch(handler) { block() }

    internal inline fun ViewModel.catchException(crossinline block: (exception: Throwable) -> Unit): CoroutineExceptionHandler {
        return CoroutineExceptionHandler { _, exception ->
            block(exception)
        }
    }
}