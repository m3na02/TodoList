package com.example.todolist.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todolist.screens.homescreen.HomeScreen
import com.example.todolist.screens.updatescreen.UpdateScreen
import com.example.todolist.viewmodel.TodoViewModel

@Composable
fun NavigationApp(TodoViewModel: TodoViewModel){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.HomeScreen.name
    ){
        composable(route = Screens.HomeScreen.name)
        {
            HomeScreen(
                TodoViewModel = TodoViewModel,
                onUpdate = {id ->
                    navController.navigate( route = "${Screens.UpdateScreen.name}/$id" )
                })
        }
        composable(
            route = "${Screens.UpdateScreen.name}/{id}",
            arguments = listOf(navArgument("id"){
                type = NavType.IntType
            }),
            enterTransition = {
                slideInHorizontally (
                    initialOffsetX = { -it },
                    animationSpec = tween(300)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -it },
                    animationSpec = tween(300)
                )
            }
        ){ navBackStackEntry ->
            navBackStackEntry.arguments?.getInt("id").let { id ->
                UpdateScreen(
                    id = id!!,
                    TodoViewModel = TodoViewModel,
                    onBack = { navController.popBackStack()})
            }
        }
    }
}
