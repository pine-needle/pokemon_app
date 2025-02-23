package com.pineneedle.pokemonapp.ui.single_pokemon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class SharedViewModel : ViewModel()

 // example
    private val _message = MutableLiveData<String>()
    val message: LiveData<String> get() = _message

    fun setMessage(msg: String) {
        _message.value = msg
    }
}
