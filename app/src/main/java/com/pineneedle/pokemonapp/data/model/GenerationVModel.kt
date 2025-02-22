package com.pineneedle.pokemonapp.data.model

import com.google.gson.annotations.SerializedName

data class GenerationVModel(
    @SerializedName("black-white")
    val blackWhite: BlackWhiteModel
)