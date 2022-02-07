package com.neyogiry.android.sample.pokedex.ui.list

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neyogiry.android.sample.pokedex.R

@ExperimentalFoundationApi
@Composable
fun Pokedex() {
    Scaffold(
        topBar = { PokedexAppBar() },
    ) {
        PokemonList()
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
fun PokemonList() {
    val list = (1..10).toList()
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        modifier = Modifier.padding(10.dp),
    ) {
        items(list.size) { value ->
            PokemonItem(item = "$value")
        }
    }
}

@Composable
fun PokemonItem(item: String) {
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
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "item"
        )
        Text(
            text = item,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
        )
    }

}

@ExperimentalFoundationApi
@Preview
@Composable
fun PokedexPreview() {
    Pokedex()
}