package com.neyogiry.android.sample.pokedex.ui.list

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.neyogiry.android.sample.pokedex.domain.Pokemon
import com.neyogiry.android.sample.pokedex.ui.theme.Pokedex
import com.neyogiry.android.sample.pokedex.util.Image

@ExperimentalFoundationApi
@Composable
fun Home(
    viewModel: HomeViewModel = viewModel()
) {
    val viewState by viewModel.state.collectAsState()
    HomeContent(
        PokemonList = viewState.pokemonList
    )

}

@ExperimentalFoundationApi
@Composable
fun HomeContent(
    PokemonList: List<Pokemon>,
) {
    Scaffold(
        topBar = { PokedexAppBar() },
    ) {
        PokemonList(PokemonList)
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
    list: List<Pokemon>
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        contentPadding = PaddingValues(all = 10.dp)
    ) {
        items(list) { item ->
            PokemonItem(pokemon = item)
        }
    }
}

@Composable
fun PokemonItem(pokemon: Pokemon) {
    val shape = RoundedCornerShape(10.dp)
    var backgroundColor by remember { mutableStateOf(Color.White) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
            .border(width = 1.dp, color = Color.Gray, shape = shape)
            .background(color = backgroundColor, shape = shape)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(url = pokemon.imageUrl, averageColor = { backgroundColor = it })
        Text(
            text = pokemon.name,
            fontSize = 16.sp,
        )
    }

}

@ExperimentalFoundationApi
@Preview
@Composable
fun PokedexPreview() {
    HomeContent(
        listOf(
            Pokemon("Bulbasaur", ""),
            Pokemon("Ivysaur", ""),
            Pokemon("Venusaur", ""),
            Pokemon("Charmander", ""),
            Pokemon("Charmeleon", ""),
            Pokemon("Charizard", ""),
            Pokemon("Squirtle", "")
        )
    )
}