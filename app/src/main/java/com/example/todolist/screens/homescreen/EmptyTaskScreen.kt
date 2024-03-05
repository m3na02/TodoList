package com.example.todolist.screens.homescreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun EmptyTaskScreen(paddingValues: PaddingValues){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "No Tasks Yet!!",
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.SemiBold,
            fontSize = 28.sp
        )
    }
}