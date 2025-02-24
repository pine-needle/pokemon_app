package com.pineneedle.pokemonapp.ui.search_pokemon


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pineneedle.pokemonapp.R
import com.pineneedle.pokemonapp.databinding.FragmentSearchPokemonBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchPokemonFragment : Fragment() {
    private var _binding: FragmentSearchPokemonBinding? = null
    private val viewModel: SearchPokemonViewModel by viewModels()
    private val binding get() = _binding!!

    private lateinit var adapter: SearchPokemonAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSearchPokemonBinding.inflate(inflater, container, false)

        adapter = SearchPokemonAdapter(emptyList()) { selectedPokemon ->
            navigateToSinglePokemonFragment(selectedPokemon)
        }

        binding.namesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.namesRecyclerView.adapter = adapter

        viewModel.pokemonNamesListData.observe(viewLifecycleOwner) { names ->
            adapter.updateList(names)
        }

        viewModel.getPokemonNamesList()

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter(newText ?: "")
                return true
            }
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun navigateToSinglePokemonFragment(pokemonName: String) {

        viewModel.getPokemonDetailsByName(pokemonName)

        viewModel.pokemonDetailsData.observe(viewLifecycleOwner) { pokemon ->
            if (pokemon != null) {

                val bundle = Bundle().apply {
                    putString("pokemon_name", pokemon.name)
                    putStringArrayList(
                        "pokemon_moves",
                        ArrayList(pokemon.moves.map { it.move.name })
                    )

                    pokemon.stats[0].base_stat?.let { putInt("pokemon_hp", it) }
                    pokemon.stats[1].base_stat?.let { putInt("pokemon_attack", it) }
                    pokemon.stats[2].base_stat?.let { putInt("pokemon_defense", it) }
                    pokemon.stats[3].base_stat?.let {
                        putInt(
                            "pokemon_special_attack",
                            it
                        )
                    }
                    pokemon.stats[4].base_stat?.let {
                        putInt(
                            "pokemon_special_defense",
                            it
                        )
                    }
                    pokemon.stats[5].base_stat?.let { putInt("pokemon_speed", it) }
                    putString(
                        "pokemon_image",
                        pokemon.sprites.other.home.front_default
                    )
                }

                findNavController().navigate(R.id.singlePokemonFragment, bundle)

            }
        }

    }
}