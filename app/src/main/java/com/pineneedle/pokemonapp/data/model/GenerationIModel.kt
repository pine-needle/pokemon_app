package com.pineneedle.pokemonapp.data.model

import com.google.gson.annotations.SerializedName

data class GenerationIModel(
    @SerializedName("red-blue")
    val redBlue: RedBlueModel,
    val yellow: YellowModel
)