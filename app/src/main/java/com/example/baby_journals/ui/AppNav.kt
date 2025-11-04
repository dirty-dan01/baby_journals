package com.example.baby_journals.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.baby_journals.ui.screens.AddBabyScreen
import com.example.baby_journals.ui.screens.BabyDetailsScreen
import com.example.baby_journals.ui.screens.BabyListScreen

object Routes {
    const val LIST = "list"
    const val ADD = "add"
    const val DETAIL = "detail/{id}"
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNav(vm: BabyViewModel) {
    val nav = rememberNavController()
    NavHost(navController = nav, startDestination = Routes.LIST) {
        composable(Routes.LIST) {
            BabyListScreen(
                state = vm.state,
                onAddClick = { nav.navigate(Routes.ADD) },
                onOpen = { id -> nav.navigate("detail?$id") }
            )
        }
        composable(Routes.ADD) {
            AddBabyScreen(
                onSave = { name, birth, h, w, bt, photo, notes ->
                    vm.addBaby(name, birth, h, w, bt, photo, notes)
                    nav.popBackStack()
                },
                onCancel = { nav.popBackStack() }
            )
        }
        composable(
            Routes.DETAIL,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        )
        { backStack ->
            val id = backStack.arguments?.getString("id")!!
            BabyDetailsScreen(
                id = id,
                state = vm.state,
                onBack = { nav.popBackStack() }
            )
        }
    }
}