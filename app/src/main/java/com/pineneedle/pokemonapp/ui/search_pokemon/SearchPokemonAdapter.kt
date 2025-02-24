package com.pineneedle.pokemonapp.ui.search_pokemon


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pineneedle.pokemonapp.R
import java.util.Locale


class SearchPokemonAdapter(
    private var originalList: List<String>,
    private val onItemClick: (String) -> Unit): RecyclerView.Adapter<SearchPokemonAdapter.PokemonViewHolder>() {

    private var filteredList: List<String> = originalList.toList()

    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvPokemonName: TextView = itemView.findViewById(R.id.tvPokemonName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pokemon_name, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemonName = filteredList[position]
        holder.tvPokemonName.text = pokemonName

        holder.itemView.setOnClickListener {
            onItemClick(pokemonName)
        }
    }

    override fun getItemCount(): Int = filteredList.size

    fun updateList(newList: List<String>) {
        originalList = newList
        filteredList = newList.toList()
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        filteredList = if (query.isEmpty()) {
            originalList
        } else {
            originalList.filter {
                it.lowercase(Locale.getDefault()).contains(query.lowercase(Locale.getDefault()))
            }
        }
        notifyDataSetChanged()
    }
}