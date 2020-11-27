package com.example.applicationpokemon.ui.pokemon.data.repository

import com.example.applicationpokemon.ui.pokemon.data.datasource.IPokemonApiHelper
import javax.inject.Inject

class PokemonRepository  @Inject constructor(val pokemonApiHelper : IPokemonApiHelper)
{
    suspend fun listaPokemon() = pokemonApiHelper.getPokemon()
}