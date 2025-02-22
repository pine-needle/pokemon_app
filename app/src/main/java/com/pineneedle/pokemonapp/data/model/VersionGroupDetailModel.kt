package com.pineneedle.pokemonapp.data.model

data class VersionGroupDetailModel(
    val level_learned_at: Int,
    val move_learn_method: MoveLearnMethodModel,
    val version_group: VersionGroupModel
)