package com.neyogiry.android.sample.pokedex.domain

import kotlinx.coroutines.flow.Flow

interface DataSource {
    val pokedex: Flow<List<Pokemon>>
    fun fetchPokemonDetailByUrl(url: String): Flow<PokemonDetail>
}