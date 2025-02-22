package com.pineneedle.pokemonapp.data.model

import com.google.gson.annotations.SerializedName

data class GenerationIiiModel(
    val emerald: EmeraldModel,

    @SerializedName("firered-leafgreen")
    val fireredLeafgreen: FireredLeafgreenModel,

    @SerializedName("ruby-sapphire")
    val rubySapphire: RubySapphireModel
)