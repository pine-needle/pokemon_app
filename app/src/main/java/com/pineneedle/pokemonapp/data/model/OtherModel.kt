package com.pineneedle.pokemonapp.data.model

import com.google.gson.annotations.SerializedName

data class OtherModel(
    val dream_world: DreamWorldModel,
    val home: HomeModel,

    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtworkModel,
    val showdown: ShowdownModel
)