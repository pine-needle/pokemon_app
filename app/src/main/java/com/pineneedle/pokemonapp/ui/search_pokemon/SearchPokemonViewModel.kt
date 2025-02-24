package com.pineneedle.pokemonapp.ui.search_pokemon


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pineneedle.pokemonapp.data.api.ApiDetails
import com.pineneedle.pokemonapp.data.model.PokemonModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchPokemonViewModel @Inject constructor(private val _apiDetails: ApiDetails): ViewModel() {

    private val _pokemonNamesList: MutableLiveData<MutableList<String>> = MutableLiveData()
    val pokemonNamesListData: LiveData<MutableList<String>> = _pokemonNamesList

    private val _pokemonDetails: MutableLiveData<PokemonModel> = MutableLiveData()
    val pokemonDetailsData: LiveData<PokemonModel> = _pokemonDetails

    fun getPokemonNamesList() {
        viewModelScope.launch {
            val tempPokemonNamesList = mutableListOf<String>()
            val response = _apiDetails.getPokemonList(limit="10000", offset = "0")
            for(eachResult in response.results){
               tempPokemonNamesList.add(eachResult.name!!)
            }
            _pokemonNamesList.postValue(tempPokemonNamesList)
        }
    }

    fun getPokemonDetailsByName(name: String){
        viewModelScope.launch {
            val response = _apiDetails.getPokemonDetailsByName(name)
            _pokemonDetails.postValue(response)
        }
    }

}