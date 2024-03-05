package com.example.todolist.screens.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.todolist.R
import com.example.todolist.SnackBars
import com.example.todolist.topAppBarTextStyle
import com.example.todolist.viewmodel.TodoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    TodoViewModel: TodoViewModel,
    onUpdate: (id: Int) -> Unit
) {
    val todos by TodoViewModel.getAllTodos.collectAsStateWithLifecycle(initialValue = emptyList())
    
    var openDialog by rememberSaveable { mutableStateOf(false) }
    
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState()}

    
    Scaffold(
        snackbarHost = { SnackbarHost (hostState = snackbarHostState)},
        topBar = {
            TopAppBar(title = {
                Text(
                    text = "ToDo List App",
                    style = topAppBarTextStyle,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { openDialog = true },
                ) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = null)

            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize()){
            Image(
                painter = painterResource(id = R.drawable.background),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.matchParentSize())
        }
        AlertDialog_Home(
            openDialog = openDialog,
            onClose = { openDialog = false },
            TodoViewModel = TodoViewModel)

        if (todos.isEmpty()){
            EmptyTaskScreen(paddingValues = paddingValues)
        } else{
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ){
                items(
                    items = todos,
                    key = {it.id}) { todo ->
                    TodoCard(
                        todo = todo,
                        onDone = {
                            TodoViewModel.deleteTodo(todo = todo)
                            SnackBars(
                                scope = scope,
                                snackbarHostState = snackbarHostState,
                                message = "Completed!! -> \"${todo.task}\"",
                                actionLabel = "Undo",
                                onAction = { TodoViewModel.undoDeletedTodo() }
                            )
                        },
                        onUpdate = onUpdate
                    )

                }
            }
        }
    }
}