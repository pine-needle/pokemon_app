package com.pineneedle.pokemonapp.ui.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.pineneedle.pokemonapp.data.model.PokemonListModel
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
    ): View? {

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

    private fun setupUI(pokemonListModel: PokemonListModel ) {
        binding.recyclerView.apply {
            adapter = DashboardAdapter(pokemonListModel.result)
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

}