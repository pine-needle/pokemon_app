package com.pineneedle.pokemonapp.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.pineneedle.pokemonapp.data.model.PokemonModel
import com.pineneedle.pokemonapp.databinding.ItemPokemonBinding

class DashboardAdapter(
    val pokemonList: List<PokemonModel>,
) : RecyclerView.Adapter<DashboardAdapter.DashboardViewHolder>() {

    class DashboardViewHolder(
        val binding: ItemPokemonBinding,
    ) : ViewHolder(binding.root) {

        fun bindData(pokemon: PokemonModel) {
            binding.tvPokemonName.text = pokemon.name
            binding.tvBaseExperience.text = "Expereince: ${pokemon.base_experience}"

            Glide.with(itemView.context)
                .load(pokemon.sprites.other.home.front_default)
                .into(binding.imgPokemon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {

        val binding = ItemPokemonBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return DashboardViewHolder(binding)
    }

    override fun getItemCount(): Int = pokemonList.size

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        holder.bindData(pokemonList[position])
    }
}