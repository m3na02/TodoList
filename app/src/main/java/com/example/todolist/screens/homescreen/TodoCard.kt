package com.example.todolist.screens.homescreen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.todolist.domain.model.Todo
import com.example.todolist.taskTextStyle

@Composable
fun TodoCard(
    todo: Todo,
    onDone: () -> Unit,
    onUpdate: (id: Int) -> Unit
){
    // card containing the todo item
    Card(
        modifier = Modifier
            .fillMaxWidth()
    ){
        // row containing the todo item content
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            IconButton(onClick = { onDone() }) {
                Icon(
                    imageVector = Icons.Rounded.CheckCircle,
                    tint = Color(204, 150, 227, 255),
                    contentDescription = null
                )
            }
            Text(
                text = todo.task,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .weight(8f),
                style = taskTextStyle
            )
            if (todo.isStar){
                Icon(
                    imageVector = Icons.Rounded.Star,
                    tint = Color(204, 150, 227, 255),
                    contentDescription = null,
                    modifier = Modifier
                        .weight(1f)
                )
            }
            IconButton(
                onClick = { onUpdate(todo.id) },
                modifier = Modifier
                    .weight(1f)
            ){
                Icon(
                    imageVector = Icons.Rounded.Edit,
                    tint = Color(204, 150, 227, 255),
                    contentDescription = null)

            }
        }
    }
}