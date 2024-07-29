package com.pachuho.lolworldview.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pachuho.lolworldview.ui.screen.champion.ChampionScreen
import com.pachuho.lolworldview.ui.screen.tag.TagScreen

enum class LeagueOfLegendScreen {
    Role,
    Detail,
    Champion
}

@Composable
fun LeagueOfLegendApp(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = LeagueOfLegendScreen.Role.name
    ) {
        composable(route = LeagueOfLegendScreen.Role.name) {
            TagScreen {
                navController.navigate("${LeagueOfLegendScreen.Champion.name}/$it")
            }
        }
        composable(
            route = LeagueOfLegendScreen.Champion.name + "/{tag}",
            arguments = listOf(navArgument("tag") { type = NavType.StringType }),
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("tag").let {
                Log.i("ChampionScreen", "call")
                ChampionScreen(it, navController)
            }
        }
        composable(route = LeagueOfLegendScreen.Detail.name) {

        }
    }
}