package com.neyogiry.android.sample.pokedex.ui.list

import android.net.Uri
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.gson.Gson
import com.neyogiry.android.sample.pokedex.domain.Pokemon
import com.neyogiry.android.sample.pokedex.ui.PokedexDestinations
import com.neyogiry.android.sample.pokedex.ui.theme.Pokedex
import com.neyogiry.android.sample.pokedex.util.Image
import com.neyogiry.android.sample.pokedex.util.ImageHelper

@ExperimentalFoundationApi
@Composable
fun Home(
    navController: NavController,
    viewModel: HomeViewModel = viewModel()
) {
    val viewState by viewModel.state.collectAsState()
    HomeContent(
        navController = navController,
        viewModel = viewModel,
        PokemonList = viewState.pokemonList
    )

}

@ExperimentalFoundationApi
@Composable
fun HomeContent(
    navController: NavController,
    viewModel: HomeViewModel,
    PokemonList: List<Pokemon>,
) {
    Scaffold(
        topBar = { PokedexAppBar() },
    ) {
        PokemonList(navController, viewModel, PokemonList)
    }
}

@Composable
fun PokedexAppBar() {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Pokedex)
    TopAppBar(
        backgroundColor = Pokedex,
    ) {
        Text(
            text = "Pokedex",
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            textAlign = TextAlign.Center
        )
    }
}

@ExperimentalFoundationApi
@Composable
fun PokemonList(
    navController: NavController,
    viewModel: HomeViewModel,
    list: List<Pokemon>
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        contentPadding = PaddingValues(all = 10.dp)
    ) {
        itemsIndexed(list) { index, item ->
            PokemonItem(
                pokemon = item,
                onUpdatePokemonColor = { viewModel.savePokemonColor(index, it) }
            ) {
                val json = Uri.encode(Gson().toJson(item))
                navController.navigate(PokedexDestinations.DETAILS_ROUTE + json)
            }
        }
    }
}

@Composable
fun PokemonItem(
    pokemon: Pokemon,
    onUpdatePokemonColor: (Color) -> Unit,
    onClick: () -> Unit
) {
    val shape = RoundedCornerShape(10.dp)
    pokemon.averageColor?.let {
        PokemonItemContent(pokemon = pokemon, shape = shape, onClick = onClick)
    } ?: run {
        PokemonItemContent(pokemon = pokemon, shape = shape, onUpdatePokemonColor = onUpdatePokemonColor, onClick = onClick)
    }
}

@Composable
fun PokemonItemContent(
    pokemon: Pokemon,
    shape: Shape,
    onUpdatePokemonColor: (Color) -> Unit,
    onClick: () -> Unit,
) {
    var backgroundColor by remember { mutableStateOf(Color.Transparent) }
    Column(
        modifier = Modifier
            .clickable { onClick() }
            .fillMaxSize()
            .padding(4.dp)
            .border(width = 1.dp, color = Color.Gray, shape = shape)
            .background(color = backgroundColor, shape = shape)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(url = ImageHelper.pokemonImageUrl(pokemon.url), averageColor = { backgroundColor = it; onUpdatePokemonColor(it) })
        Text(
            text = pokemon.name,
            fontSize = 16.sp,
        )
    }
}

@Composable
fun PokemonItemContent(
    pokemon: Pokemon,
    shape: Shape,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .clickable { onClick() }
            .fillMaxSize()
            .padding(4.dp)
            .border(width = 1.dp, color = Color.Gray, shape = shape)
            .background(color = pokemon.averageColor ?: Color.Transparent, shape = shape)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(url = ImageHelper.pokemonImageUrl(pokemon.url))
        Text(
            text = pokemon.name,
            fontSize = 16.sp,
        )
    }
}

//@ExperimentalFoundationApi
//@Preview
//@Composable
//fun PokedexPreview() {
//    HomeContent(
//        rememberNavController(),
//        listOf(
//            Pokemon("Bulbasaur", ""),
//            Pokemon("Ivysaur", ""),
//            Pokemon("Venusaur", ""),
//            Pokemon("Charmander", ""),
//            Pokemon("Charmeleon", ""),
//            Pokemon("Charizard", ""),
//            Pokemon("Squirtle", "")
//        )
//    )
//}