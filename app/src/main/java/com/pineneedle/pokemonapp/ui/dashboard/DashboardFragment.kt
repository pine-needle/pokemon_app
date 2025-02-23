package com.pineneedle.pokemonapp.ui.dashboard
import android.util.Log

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.pineneedle.pokemonapp.R
import com.pineneedle.pokemonapp.data.model.PokemonListModel
import com.pineneedle.pokemonapp.data.model.PokemonModel
import com.pineneedle.pokemonapp.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {


    private var _binding: FragmentDashboardBinding? = null
    private val viewModel: DashboardViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        viewModel.pokemonListData.observe(viewLifecycleOwner) {
            setupUI(it)
        }

        viewModel.getPokemonList()


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun setupUI(pokemonListModel: PokemonListModel) {
        binding.recyclerView.apply {
            adapter = DashboardAdapter(pokemonListModel.result) { pokemon -> // Accepts full PokemonModel
                openPokemonDetails(pokemon) // Pass the full model
            }
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }


    // create a bundle and pass it to the SinglePokemonFragment by find nav
    private fun openPokemonDetails(pokemon: PokemonModel) {
        val bundle = Bundle().apply {
            putString("pokemon_name", pokemon.name)
            putStringArrayList("pokemon_moves", ArrayList(pokemon.moves.map { it.move.name })) // Moves
            pokemon.stats[0].base_stat?.let { putInt("pokemon_hp", it) } // HP
            pokemon.stats[1].base_stat?.let { putInt("pokemon_attack", it) } // Attack
            pokemon.stats[2].base_stat?.let { putInt("pokemon_defense", it) } // Defense
            pokemon.stats[3].base_stat?.let { putInt("pokemon_special_attack", it) } // Special Attack
            pokemon.stats[4].base_stat?.let { putInt("pokemon_special_defense", it) } // Special Defense
            pokemon.stats[5].base_stat?.let { putInt("pokemon_speed", it) } // Speed
            putString("pokemon_image", pokemon.sprites.other.home.front_default) // Image URL

        }
        findNavController().navigate(R.id.singlePokemonFragment, bundle)
    }





}