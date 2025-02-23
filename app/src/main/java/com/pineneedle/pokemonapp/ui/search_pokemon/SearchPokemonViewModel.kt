package com.pineneedle.pokemonapp.ui.search_pokemon

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pineneedle.pokemonapp.data.api.ApiDetails
import com.pineneedle.pokemonapp.data.model.PokemonListModel
import com.pineneedle.pokemonapp.data.model.PokemonModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchPokemonViewModel @Inject constructor(
    private val apiDetails: ApiDetails
) : ViewModel() {

    private val _pokemonList = MutableStateFlow<List<PokemonModel>>(emptyList())
    val pokemonList: StateFlow<List<PokemonModel>> = _pokemonList

    private var allPokemon: List<PokemonModel> = emptyList()

    init {
        fetchAllPokemon()
    }

    private fun fetchAllPokemon() {
        viewModelScope.launch {
            try {
                val response: PokemonListModel = apiDetails.getPokemonLists()
                allPokemon = response.result
                _pokemonList.value = allPokemon
                Log.d("SearchPokemonViewModel", "Fetched Pokemon: ${allPokemon.size}")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun searchPokemonByPrefix(prefix: String) {
        viewModelScope.launch {
            if (prefix.isEmpty()) {
                _pokemonList.value = allPokemon
            } else {
                val filteredList = allPokemon.filter {
                    it.name?.startsWith(prefix, ignoreCase = true) == true
                }
                _pokemonList.value = filteredList
                Log.d("SearchPokemonViewModel", "Filtered List: ${filteredList.size}")
            }
        }
    }
}
