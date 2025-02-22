package com.pineneedle.pokemonapp.data.model

import com.google.gson.annotations.SerializedName

data class GenerationViiModel(
    val icons: IconsModel,

    @SerializedName("ultra-sun-ultra-moon")
    val ultraSunUltraMoon: UltraSunUltraMoonModel
)