package com.neyogiry.android.sample.pokedex.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.neyogiry.android.sample.pokedex.ui.detail.PokemonDetails
import com.neyogiry.android.sample.pokedex.ui.list.Home

@ExperimentalFoundationApi
@Composable
fun PokedexNavGraph(
    navController: NavHostController = rememberNavController()
) {
    
    NavHost(
        navController = navController,
        startDestination = PokedexDestinations.HOME_ROUTE,
    ) {
        composable(PokedexDestinations.HOME_ROUTE) {
            Home(navController)
        }
        composable("${PokedexDestinations.DETAILS_ROUTE}{url}") { backStackEntry ->
            backStackEntry.arguments?.getString("url")?.let { url -> PokemonDetails(navController = navController, url = url) }
        }
    }

}

object PokedexDestinations {
    const val HOME_ROUTE = "home"
    const val DETAILS_ROUTE = "details/"
}