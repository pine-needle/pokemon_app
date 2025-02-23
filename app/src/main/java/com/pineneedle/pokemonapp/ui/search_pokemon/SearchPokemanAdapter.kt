package com.pineneedle.pokemonapp.ui.search_pokemon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pineneedle.pokemonapp.R
import com.pineneedle.pokemonapp.data.model.PokemonModel

class SearchPokemonAdapter(
    private var pokemonList: List<PokemonModel>
) : RecyclerView.Adapter<SearchPokemonAdapter.SearchPokemonViewHolder>() {

    class SearchPokemonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val pokemonImage: ImageView = view.findViewById(R.id.imgPokemon)
        private val pokemonName: TextView = view.findViewById(R.id.tvPokemonName)

        fun bind(pokemon: PokemonModel) {
            pokemonName.text = pokemon.name
            Glide.with(itemView.context)
                .load(pokemon.sprites.other.home.front_default)
                .into(pokemonImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchPokemonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pokemon, parent, false)
        return SearchPokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchPokemonViewHolder, position: Int) {
        holder.bind(pokemonList[position])
    }

    override fun getItemCount(): Int = pokemonList.size

    fun updateList(newList: List<PokemonModel>) {
        pokemonList = newList
        notifyDataSetChanged()
    }
}
