package com.henrydev.welcomehome.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.henrydev.welcomehome.ui.home.HomeScreen
import com.henrydev.welcomehome.ui.screen.PersonEntryScreen

@Composable
fun PersonNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier
    ) {

        composable(
            route = "home"
        ) {
            HomeScreen(
                navigateToEntryPerson = { navController.navigate("entry_person") },
                navigateBack = { navController.navigateUp() }
            )
        }

        composable(
            route = "entry_person"
        ) {
            PersonEntryScreen(
                navigateBack = { navController.navigateUp() }
            )
        }

    }
}















