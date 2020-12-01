package com.example.applicationpokemon.ui.pokemondetail.data.db

import androidx.lifecycle.LiveData
import com.example.applicationpokemon.ui.pokemon.data.model.Pokemon
import javax.inject.Inject

class PokemonDetailHelperImpl @Inject constructor(private val pokemonDetail: PokemonDetailDao)
    : IPokemonDetailsDaoHelper {

    override suspend fun upsert(pokemon: Pokemon): Long = pokemonDetail.upsert(pokemon)

    override fun getAllPokemon(): LiveData<List<Pokemon>> = pokemonDetail.getAllPokemon()

    override suspend fun deletePokemon(pokemon: Pokemon) {
        pokemonDetail.deletePokemon(pokemon)
    }
}