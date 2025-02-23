package com.pineneedle.pokemonapp.ui.search_pokemon

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pineneedle.pokemonapp.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class SearchPokemonFragment : Fragment() {

    private val viewModel: SearchPokemonViewModel by viewModels()
    private lateinit var adapter: SearchPokemonAdapter
    private lateinit var searchEditText: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_search_pokemon, container, false)

        searchEditText = view.findViewById(R.id.searchEditText)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        adapter = SearchPokemonAdapter(emptyList())
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        // TextWatcher for search functionality
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val query = s.toString()
                Log.d("SearchPokemonFragment", "Searching for: $query")
                viewModel.searchPokemonByPrefix(query)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // Observe the pokemonList and update RecyclerView
        lifecycleScope.launchWhenStarted {
            viewModel.pokemonList.collectLatest { pokemonList ->
                Log.d("SearchPokemonFragment", "Pokemon List Updated: ${pokemonList.size}")
                adapter.updateList(pokemonList)
            }
        }

        return view
    }
}
