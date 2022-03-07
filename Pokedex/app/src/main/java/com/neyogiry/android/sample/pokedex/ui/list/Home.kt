package com.neyogiry.android.sample.pokedex.ui.list

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.neyogiry.android.sample.pokedex.domain.Pokemon

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
    TopAppBar() {
        Text(text = "Pokedex")
    }
}

@ExperimentalFoundationApi
@Composable
fun PokemonList(
    list: List<Pokemon>
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        modifier = Modifier.padding(10.dp),
    ) {
        items(list) { item ->
            PokemonItem(pokemon = item)
        }
    }
}

@Composable
fun PokemonItem(pokemon: Pokemon) {
    val shape = RoundedCornerShape(10.dp)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
            .border(width = 1.dp, color = Color.Gray, shape = shape)
            .background(color = Color.White, shape = shape)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(model = pokemon.imageUrl, contentDescription = null)
        Text(
            text = pokemon.name,
            textAlign = TextAlign.Center,
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