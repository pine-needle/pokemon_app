package com.pineneedle.pokemonapp.ui.dashboard


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.pineneedle.pokemonapp.R
import com.pineneedle.pokemonapp.data.model.PokemonModel
import com.pineneedle.pokemonapp.databinding.ItemPokemonBinding


class DashboardAdapter(
    val pokemonList: List<PokemonModel>,
    private val onPokemonClick: (PokemonModel) -> Unit
) : RecyclerView.Adapter<DashboardAdapter.DashboardViewHolder>() {

    class DashboardViewHolder(val view: View) :
        ViewHolder(view) {
        val pokemonImage = view.findViewById<ImageView>(R.id.imgPokemon)
        val pokemonName = view.findViewById<TextView>(R.id.tvPokemonName)
        val pokemonExperience = view.findViewById<TextView>(R.id.tvBaseExperience)

        fun bindData(pokemon: PokemonModel, onPokemonClick: (PokemonModel) -> Unit) {
            pokemonName.text = pokemon.name
            pokemonExperience.text = pokemon.base_experience.toString()

            Glide.with(itemView.context)
                .load(pokemon.sprites.other.home.front_default)
                .into(pokemonImage)

           ItemPokemonBinding.bind(view).btnViewDetails.setOnClickListener {
                onPokemonClick(pokemon)
            }
        }
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        holder.bindData(pokemon, onPokemonClick)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        return DashboardViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        )
    }

    override fun getItemCount(): Int = pokemonList.size

}