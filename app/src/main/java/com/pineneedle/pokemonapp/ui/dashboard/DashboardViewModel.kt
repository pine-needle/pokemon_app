package com.pineneedle.pokemonapp.ui.dashboard

import androidx.core.util.rangeTo
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pineneedle.pokemonapp.data.api.ApiDetails
import com.pineneedle.pokemonapp.data.model.PokemonListModel
import com.pineneedle.pokemonapp.data.model.PokemonModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val _apiDetails: ApiDetails): ViewModel() {

    private val _pokemonList: MutableLiveData<PokemonListModel> = MutableLiveData()
    val pokemonListData: LiveData<PokemonListModel> = _pokemonList

    fun getPokemonList() {
        viewModelScope.launch {
            val tempPokemonList = mutableListOf<PokemonModel>()

            for(i in 1.rangeTo(5)){
                tempPokemonList.add(_apiDetails.getPokemonDetails(i))
            }

            _pokemonList.postValue(PokemonListModel(tempPokemonList))
        }
    }


}