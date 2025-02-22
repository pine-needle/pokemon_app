package com.pineneedle.pokemonapp.data.model

import com.google.gson.annotations.SerializedName

data class VersionsModel(
    @SerializedName("generation-i")
    val generation1: GenerationIModel,

    @SerializedName("generation-ii")
    val generation2: GenerationIiModel,

    @SerializedName("generation-iii")
    val generation3: GenerationIiiModel,

    @SerializedName("generation-iv")
    val generation4: GenerationIvModel,

    @SerializedName("generation-v")
    val generation5: GenerationVModel,

    @SerializedName("generation-vi")
    val generation6: GenerationViModel,

    @SerializedName("generation-vii")
    val generation7: GenerationViiModel,

    @SerializedName("generation-viii")
    val generation8: GenerationViiiModel
)