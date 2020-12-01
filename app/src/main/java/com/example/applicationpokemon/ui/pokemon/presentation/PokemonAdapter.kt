package com.example.applicationpokemon.ui.pokemon.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.applicationpokemon.R
import com.example.applicationpokemon.core.utils.Constrants.URL_IMG
import com.example.applicationpokemon.ui.pokemon.data.model.Pokemon
import kotlinx.android.synthetic.main.item_pokemon.view.*
import java.util.*

class PokemonAdapter : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    inner class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_pokemon,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = differ.currentList[position]
        holder.itemView.apply {
            item_pokemon_txt_name.text = pokemon.name.toUpperCase(Locale.ROOT)

            val urlCortada = pokemon.url.substring(34, pokemon.url.length - 1)
            val image = "$URL_IMG$urlCortada.png"

            item_pokemon_img.load(image) {
                crossfade(true)
                crossfade(500)
            }
            setOnClickListener {
                onItemClickListener?.let { it(pokemon) }
            }
        }
    }

    private var onItemClickListener: ((Pokemon) -> Unit)? = null

    fun setOnItemClickListener(listener: (Pokemon) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}