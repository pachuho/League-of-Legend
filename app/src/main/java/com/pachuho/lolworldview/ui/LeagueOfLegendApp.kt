package com.pachuho.lolworldview.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pachuho.lolworldview.R
import com.pachuho.lolworldview.Screen
import com.pachuho.lolworldview.data.model.Champion
import com.pachuho.lolworldview.ui.screen.champion.ChampionScreen
import com.pachuho.lolworldview.ui.screen.detail.DetailScreen
import com.pachuho.lolworldview.ui.screen.tag.TagScreen
import com.pachuho.lolworldview.ui.utils.NavigationAnimation
import com.pachuho.lolworldview.ui.utils.deserialize
import com.pachuho.lolworldview.ui.utils.deserializeList
import com.pachuho.lolworldview.ui.utils.serialize
import com.pachuho.lolworldview.ui.utils.serializeList
import com.pachuho.lolworldview.ui.utils.showToast

@Composable
fun LeagueOfLegendApp(
    navController: NavHostController = rememberNavController()
) {
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = Screen.Role.route
    ) {
        NavigationAnimation.apply {
            composable(
                route = Screen.Role.route,
                enterTransition = enterTransition,
                exitTransition = exitTransition,
                popEnterTransition = popEnterTransition,
                popExitTransition = popExitTransition
            ) {
                TagScreen {
                    navController.navigate(
                        Screen.Champion.createRoute(it.serializeList())
                    )
                }
            }
            composable(
                route = Screen.Champion.route,
                arguments = Screen.Champion.navArguments,
                enterTransition = enterTransition,
                exitTransition = exitTransition,
                popEnterTransition = popEnterTransition,
                popExitTransition = popExitTransition
            ) { backStackEntry ->
                backStackEntry.arguments?.getString(Screen.Champion.argument).let {
                    it?.deserializeList<Champion>()?.let { champions ->
                        ChampionScreen(champions) { championId ->
                            navController.navigate(
                                Screen.Detail.createRoute(championId)
                            )
                        }
                    } ?: run {
                        LaunchedEffect(Unit) {
                            context.showToast(context.getString(R.string.fail_champions))
                        }
                    }
                }
            }
            composable(
                route = Screen.Detail.route,
                arguments = Screen.Detail.navArguments,
                enterTransition = enterTransition,
                exitTransition = exitTransition,
                popEnterTransition = popEnterTransition,
                popExitTransition = popExitTransition
            ) { backStackEntry ->
                backStackEntry.arguments?.getString(Screen.Detail.argument)?.let {
                    DetailScreen(it)
                } ?: run {
                    LaunchedEffect(Unit) {
                        context.showToast(context.getString(R.string.fail_champions))
                    }
                }
            }
        }
    }
}