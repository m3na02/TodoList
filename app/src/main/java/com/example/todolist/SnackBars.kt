package com.example.todolist

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun SnackBars(
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    message: String,
    actionLabel: String,
    onAction: () -> Unit
) {
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