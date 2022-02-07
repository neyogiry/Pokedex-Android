package com.neyogiry.android.sample.pokedex.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import com.neyogiry.android.sample.pokedex.ui.list.Pokedex
import com.neyogiry.android.sample.pokedex.ui.theme.PokedexTheme

@ExperimentalFoundationApi
@Composable
fun PokedexApp() {
    PokedexTheme {
        Pokedex()
    }
}