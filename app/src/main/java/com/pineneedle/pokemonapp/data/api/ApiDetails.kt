package com.pineneedle.pokemonapp.data.api

import com.pineneedle.pokemonapp.data.model.PokemonModel
import com.pineneedle.pokemonapp.data.model.ResponseModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiDetails {

    @GET(ApiReference.END_POINT)
    suspend fun getPokemonList(
        @Query("limit") limit: String = "10000",
        @Query("offset") offset: String = "0"
    ): ResponseModel

    @GET("${ApiReference.END_POINT}/{id}")
    suspend fun getPokemonDetails(
        @Path("id") id: Int
    ): PokemonModel

    @GET("${ApiReference.END_POINT}/{name}")
    suspend fun getPokemonDetailsByName(
        @Path("name") name: String
    ): PokemonModel
}