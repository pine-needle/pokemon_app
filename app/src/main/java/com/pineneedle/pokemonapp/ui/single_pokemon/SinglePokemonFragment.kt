package com.pineneedle.pokemonapp.ui.single_pokemon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.pineneedle.pokemonapp.databinding.FragmentSinglePokemonBinding
import dagger.hilt.android.AndroidEntryPoint

class SinglePokemonFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentSinglePokemonBinding.inflate(inflater, container, false)

        return binding.root
    }

}