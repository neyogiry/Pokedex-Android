package com.neyogiry.android.sample.pokedex.ui

import android.os.Bundle
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.neyogiry.android.sample.pokedex.domain.Pokemon
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
        composable(
            route = "${PokedexDestinations.DETAILS_ROUTE}{pokemon}",
            arguments = listOf(navArgument("pokemon") { type = PokemonType() })
        ) { backStackEntry ->
            backStackEntry.arguments?.getParcelable<Pokemon>("pokemon")?.let { pokemon -> PokemonDetails(navController = navController, pokemon = pokemon) }
        }
    }

}

object PokedexDestinations {
    const val HOME_ROUTE = "home"
    const val DETAILS_ROUTE = "details/"
}

class PokemonType : NavType<Pokemon>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Pokemon? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): Pokemon {
        return Gson().fromJson(value, Pokemon::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: Pokemon) {
        bundle.putParcelable(key, value)
    }

}