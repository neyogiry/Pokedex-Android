package com.neyogiry.android.sample.pokedex.data

import com.google.gson.annotations.SerializedName

data class PokemonDetailResponse(
    @SerializedName("name") val name: String?,
    @SerializedName("height") val height: Int?,
    @SerializedName("weight") val weight: Int?,
)