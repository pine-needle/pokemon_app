package com.pineneedle.pokemonapp.data.model

import com.google.gson.annotations.SerializedName

data class GenerationViModel(
    @SerializedName("omegaruby-alphasapphire")
    val omegarubyAlphasapphire: OmegarubyAlphasapphireModel,

    @SerializedName("x-y")
    val xY: XYModel
)