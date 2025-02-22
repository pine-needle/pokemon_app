package com.pineneedle.pokemonapp.data.model

data class PokemonModel(
    val abilities: List<AbilityModel>,
    val base_experience: Int?,
    val cries: CriesModel,
    val forms: List<FormModel>,
    val game_indices: List<GameIndiceModel>,
    val height: Int?,
    val held_items: List<Any?>,
    val id: Int?,
    val is_default: Boolean?,
    val location_area_encounters: String?,
    val moves: List<MoveModel>,
    val name: String?,
    val order: Int?,
    val past_abilities: List<Any?>,
    val past_types: List<Any?>,
    val species: SpeciesModel,
    val sprites: SpritesModel,
    val stats: List<StatModel>,
    val types: List<TypeModel>,
    val weight: Int?
)