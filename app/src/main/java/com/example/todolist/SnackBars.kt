package com.example.todolist

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun SnackBars(
    // coroutine scope to launch coroutine
    scope: CoroutineScope,
    // state for managing the snackbar
    snackbarHostState: SnackbarHostState,
    // message to display in snackbar
    message: String,
    // label for the action button
    actionLabel: String,
    // callback function to execute when action is performed
    onAction: () -> Unit
) {
    // launching coroutine
    scope.launch {
        snackbarHostState.currentSnackbarData?.dismiss()
        val snackbarResult = snackbarHostState.showSnackbar(
            message = message,
            actionLabel = actionLabel,
            duration = SnackbarDuration.Short
        )

        when(snackbarResult){
            SnackbarResult.ActionPerformed -> {
                onAction()
            }
            SnackbarResult.Dismissed ->{}
        }
    }

}