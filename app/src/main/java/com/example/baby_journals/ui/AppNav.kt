package com.example.baby_journals.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable

object Routes{
    const val LIST = "list"
    const val ADD = "add"
    const val DETAIL = "detail/{id}"
}

@Composable
fun AppNav(vm: BabyViewModel){
    val nav = rememberNavController()
    NavHost(navController = nav, startDestination = Routes.LIST){
        composable(Routes.LIST){
            BabyListScreen(
                state
            )
        }
    }
}