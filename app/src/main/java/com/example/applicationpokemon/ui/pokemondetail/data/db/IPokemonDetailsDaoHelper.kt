package com.example.applicationpokemon.ui.pokemondetail.data.db

import androidx.lifecycle.LiveData
import com.example.applicationpokemon.ui.pokemon.data.model.Pokemon

interface IPokemonDetailsDaoHelper {

    suspend fun upsert(pokemon: Pokemon): Long

    fun getAllPokemon() : LiveData<List<Pokemon>>

    suspend fun deletePokemon(pokemon: Pokemon)
}