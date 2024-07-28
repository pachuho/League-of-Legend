package com.pachuho.lolworldview.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
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
                navController.navigate(LeagueOfLegendScreen.Champion.name)
            }
        }
        composable(route = LeagueOfLegendScreen.Champion.name) {
            ChampionScreen {
                navController.navigate(LeagueOfLegendScreen.Detail.name)
            }
        }
        composable(route = LeagueOfLegendScreen.Detail.name) {

        }
    }
}