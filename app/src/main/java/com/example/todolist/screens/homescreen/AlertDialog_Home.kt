package com.example.todolist.screens.homescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist.ToastMessage
import com.example.todolist.domain.model.Todo
import com.example.todolist.taskTextStyle
import com.example.todolist.viewmodel.TodoViewModel
import kotlinx.coroutines.job
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.graphics.Color

@Composable
fun AlertDialog_Home(
    openDialog: Boolean,
    onClose:() -> Unit,
    TodoViewModel: TodoViewModel
){

    // mutable state for the text input and star check box
    var text by remember { mutableStateOf("") }
    var isStar by remember { mutableStateOf(false) }

    // Create a Todo object from the current text and star state
    val todo = Todo(0, text, isStar)

    // request focus for the text
    val focusRequester = FocusRequester()
    val context = LocalContext.current

    // displaying the alertdialog only if the opendialog is true
    if (openDialog){
        AlertDialog(
            title = {
                    Text(
                        text = "Todo",
                        fontFamily = FontFamily.Serif
                    )
                
            },
            text = {
                   LaunchedEffect(
                       key1 = true,
                       block = {
                           coroutineContext.job.invokeOnCompletion {
                               focusRequester.requestFocus()
                           }

                   })
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    // textfield where user enter the task
                    TextField(
                        value = text,
                        onValueChange = {text = it},
                        placeholder = {
                            Text(
                                text = "Add Task!",
                                fontFamily = FontFamily.Monospace
                            )
                        },
                        shape = RectangleShape,
                        modifier = Modifier
                            .focusRequester(focusRequester),
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor =  Color(204, 150, 227, 255),
                        ),
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Sentences,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                if (text.isNotBlank()) {
                                    // inserting the todo when done button is clicked
                                    TodoViewModel.insertTodo(todo = todo)
                                    text = ""
                                    isStar = false
                                    onClose()
                                } else {
                                    ToastMessage(
                                        context,
                                        // show a toast if the task is empty
                                        "Empty Task!!"
                                    )
                                }
                            }
                        ),
                        trailingIcon = {
                            IconButton(onClick = { text = "" }) {
                                Icon(imageVector = Icons.Rounded.Clear, contentDescription = null
                                )
                            }
                        },
                        textStyle = taskTextStyle
                    )
                    Row (
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            text = "Need To Do",
                            fontFamily = FontFamily.Monospace,
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        Checkbox(
                            checked = isStar,
                            onCheckedChange = {isStar = it},
                            colors = CheckboxDefaults.colors(
                                checkedColor =  Color(204, 150, 227, 255),
                            ),
                            )
                    }
                }
            },
            onDismissRequest = {
                onClose()
                text = ""
                isStar = false
            },
            confirmButton = {
                Button(onClick = {
                    if (text.isNotBlank()) {
                        TodoViewModel.insertTodo(todo = todo)
                        text = ""
                        isStar = false
                        onClose()
                    } else {
                        ToastMessage(
                            context,
                            "Empty Task!!"
                        )
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor =  Color(204, 150, 227, 255)
                )
                ) {
                    Text(text = "Save")
                }
            },
            dismissButton = {
                OutlinedButton(onClick = {
                    onClose()
                    text = ""
                    isStar = false
                },
                ) {
                    Text(text = "Close")

                }
            })
        }
}
