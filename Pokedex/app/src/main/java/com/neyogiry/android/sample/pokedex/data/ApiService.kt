package com.neyogiry.android.sample.pokedex.data

import retrofit2.Response
import retrofit2.http.GET

/**
 * REST API access points
 */
interface ApiService {

    @GET("pokemon")
    suspend fun pokemonList() : Response<PokedexResponse>

}