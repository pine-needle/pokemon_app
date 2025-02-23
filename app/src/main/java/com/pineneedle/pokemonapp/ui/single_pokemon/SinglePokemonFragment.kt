package com.pineneedle.pokemonapp.ui.single_pokemon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.bumptech.glide.Glide
import android.util.Log
import com.pineneedle.pokemonapp.R

class SinglePokemonFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_single_pokemon, container, false)

        // Get data from the bundle
        val pokemonName = arguments?.getString("pokemon_name")
        val pokemonMoves = arguments?.getStringArrayList("pokemon_moves")
        val pokemonHp = arguments?.getInt("pokemon_hp", 0)
        val pokemonAttack = arguments?.getInt("pokemon_attack", 0)
        val pokemonDefense = arguments?.getInt("pokemon_defense", 0)
        val pokemonSpecialAttack = arguments?.getInt("pokemon_special_attack", 0)
        val pokemonSpecialDefense = arguments?.getInt("pokemon_special_defense", 0)
        val pokemonSpeed = arguments?.getInt("pokemon_speed", 0)
        val pokemonImage = arguments?.getString("pokemon_image") // Get image URL

        // Log received data
        Log.d("SinglePokemonFragment", "Received Pokémon: $pokemonName")
        Log.d("SinglePokemonFragment", "Image URL: $pokemonImage")

        // Update UI
        view.findViewById<TextView>(R.id.txtName).text = pokemonName ?: "Unknown Pokémon"
        view.findViewById<TextView>(R.id.txtMoves).text = "Moves: ${pokemonMoves?.joinToString(", ")}"
        view.findViewById<ProgressBar>(R.id.progressHp).progress = pokemonHp ?: 0
        view.findViewById<ProgressBar>(R.id.progressAttack).progress = pokemonAttack ?: 0
        view.findViewById<ProgressBar>(R.id.progressDefense).progress = pokemonDefense ?: 0
        view.findViewById<ProgressBar>(R.id.progressSpAttack).progress = pokemonSpecialAttack ?: 0
        view.findViewById<ProgressBar>(R.id.progressSpDefense).progress = pokemonSpecialDefense ?: 0
        view.findViewById<ProgressBar>(R.id.progressSpeed).progress = pokemonSpeed ?: 0

        // Load and display image using Glide
        val imgPokemon = view.findViewById<ImageView>(R.id.imgPokemon)
        Glide.with(this)
            .load(pokemonImage)
            .into(imgPokemon)

        return view
    }
}
