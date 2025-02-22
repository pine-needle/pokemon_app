package com.pineneedle.pokemonapp.data.model

data class ResponseModel(
    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: List<ResultModel>
)