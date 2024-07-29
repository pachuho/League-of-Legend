package com.pachuho.lolworldview

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(
    val route: String,
    val navArguments: List<NamedNavArgument> = emptyList()
) {
    data object Role : Screen(
        route =  "role"
    )

    data object Champion : Screen(
        route = "champion/{champions}",
        navArguments = listOf(navArgument("champions") {
            type = NavType.StringType
        })
    ) {
        const val argument = "champions"
        fun createRoute(champions: String?) = "champion/${champions}"
    }

    data object Detail : Screen(
        route = "detail/{champion}",
        navArguments = listOf(navArgument("champion") {
            type = NavType.StringType
        })
    ) {
        const val argument = "champion"

        fun createRoute(champion: String?) = "detail/${champion}"

    }
}