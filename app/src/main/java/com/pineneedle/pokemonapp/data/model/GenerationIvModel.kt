package com.pineneedle.pokemonapp.data.model

import com.google.gson.annotations.SerializedName

data class GenerationIvModel(
    @SerializedName("diamond-pearl")
    val diamondPearl: DiamondPearlModel,

    @SerializedName("heartgold-soulsilver")
    val heartgoldSoulsilver: HeartgoldSoulsilverModel,

    val platinum: PlatinumModel
)