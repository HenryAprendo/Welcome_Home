package com.henrydev.welcomehome.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.henrydev.welcomehome.ui.home.HomeDestination
import com.henrydev.welcomehome.ui.home.HomeScreen
import com.henrydev.welcomehome.ui.screen.PersonEntryDestination
import com.henrydev.welcomehome.ui.screen.PersonEntryScreen

@Composable
fun PersonNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {

        composable(
            route = HomeDestination.route
        ) {
            HomeScreen(
                navigateToEntryPerson = { navController.navigate(PersonEntryDestination.route) },
                navigateBack = { navController.navigateUp() }
            )
        }

        composable(
            route = PersonEntryDestination.route
        ) {
            PersonEntryScreen(
                navigateBack = { navController.navigateUp() },
                navigateToUp = { navController.popBackStack() }
            )
        }

    }
}
