package com.neyogiry.android.sample.pokedex.domain

import kotlinx.coroutines.flow.Flow

class PokedexRepository(private val dataSource: DataSource) {

    val pokedex: Flow<List<Pokemon>> = dataSource.pokedex

    fun pokemonDetail(url: String) = dataSource.fetchPokemonDetailByUrl(url)

}