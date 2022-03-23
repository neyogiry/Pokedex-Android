package com.neyogiry.android.sample.pokedex.domain

data class Pokemon(val name: String, private val url: String) {

    val imageUrl: String
        get() {
            val index = url.split("/".toRegex()).dropLast(1).last()
            return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$index.png"
        }

}