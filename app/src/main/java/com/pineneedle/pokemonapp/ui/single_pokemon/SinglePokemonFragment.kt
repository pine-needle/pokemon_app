package com.pineneedle.pokemonapp.ui.single_pokemon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.pineneedle.pokemonapp.R


class SinglePokemonFragment : Fragment() {

    // how to get the data from the part to this fragment

    private lateinit var viewModel: SharedViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_single_pokemon, container, false)
        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        val textView = view.findViewById<TextView>(R.id.textView)
        viewModel.message.observe(viewLifecycleOwner) { message ->
            textView.text = message
        }

        return view
    }
}



class SecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_second, container, false)
        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        val textView = view.findViewById<TextView>(R.id.textView)
        viewModel.message.observe(viewLifecycleOwner) { message ->
            textView.text = message
        }

        return view
    }
}
