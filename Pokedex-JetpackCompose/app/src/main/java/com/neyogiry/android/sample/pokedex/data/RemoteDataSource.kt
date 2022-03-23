package com.neyogiry.android.sample.pokedex.data

import com.neyogiry.android.sample.pokedex.domain.DataSource
import com.neyogiry.android.sample.pokedex.domain.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteDataSource(
    private val apiService: ApiService = ApiClient.getApiService()
) : DataSource {

    override val pokedex: Flow<List<Pokemon>>
        get() = flow {
            val response = apiService.pokemonList()
            if(response.isSuccessful) {
                val pokemonListResponse = response.body()?.results
                val list = ArrayList<Pokemon>()
                pokemonListResponse?.forEach { item ->
                    list.add(
                        Pokemon(item.name ?: "", item.url ?: "")
                    )
                }
                emit(list)
            } else {
                //TODO: Manage error responses
            }

        }

}