package com.neyogiry.android.sample.pokedex.domain

import kotlinx.coroutines.flow.Flow

class PokedexRepository(val dataSource: DataSource) {

    val pokedex: Flow<List<Pokemon>> = dataSource.pokedex

}