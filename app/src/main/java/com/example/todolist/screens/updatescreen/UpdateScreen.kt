package com.example.todolist.screens.updatescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist.R
import com.example.todolist.taskTextStyle
import com.example.todolist.topAppBarTextStyle
import com.example.todolist.viewmodel.TodoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateScreen(
    id: Int,
    TodoViewModel: TodoViewModel,
    onBack: () -> Unit
){
    val task = TodoViewModel.todo.task
    val isStar = TodoViewModel.todo.isStar

    LaunchedEffect(
        key1 = true,
        block = {
            TodoViewModel.getTodoById(id = id)
        })
    Scaffold(
        topBar ={
            TopAppBar(title = {
                Text(
                    text = "Update ToDo",
                    style = topAppBarTextStyle
                )
            },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = null)
                        
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor =   Color(204, 150, 227, 255)
                )
                )
        }
    ) {paddingValues ->
        // background image
        Box(modifier = Modifier.fillMaxSize()){
            Image(
                painter = painterResource(id = R.drawable.background),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.matchParentSize())
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(16.dp))
            // icon edit
            Icon(
                imageVector = Icons.Rounded.Edit,
                tint = Color(0, 0, 0, 255),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
            )
            Spacer(modifier = Modifier.size(16.dp))
            TextField(
                value = task,
                onValueChange = { newvalue ->
                    TodoViewModel.updateTask(newValue = newvalue)
                },
                modifier = Modifier
                    .fillMaxWidth(.9f),
                label = {
                    Text(
                        text = "Task",
                        fontFamily = FontFamily.Monospace

                    )
                },
                shape = RectangleShape,
                keyboardOptions = KeyboardOptions(
                    KeyboardCapitalization.Sentences
                ),
                textStyle = taskTextStyle

                )
            Spacer(modifier = Modifier.size(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Need To Do",
                    fontFamily = FontFamily.Monospace,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.size(8.dp))
                Checkbox(
                    checked = isStar,
                    onCheckedChange ={
                        newValue ->
                        TodoViewModel.updateIsStar(newValue = newValue)
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor =   Color(204, 150, 227, 255),
                    ),

                )
            }
            Spacer(modifier = Modifier.size(8.dp))
            Button(onClick = {
                TodoViewModel.updateTodo(TodoViewModel.todo)
                onBack()
            },
                colors = ButtonDefaults.buttonColors(
                    containerColor =  Color(204, 150, 227, 255)
                )
                ) {
                Text(
                    text = "Save Task",
                    fontSize = 16.sp
                )
            }
        }
    }
}