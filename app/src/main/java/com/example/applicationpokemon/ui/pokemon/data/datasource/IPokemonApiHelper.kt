package com.example.applicationpokemon.ui.pokemon.data.datasource

import com.example.applicationpokemon.ui.pokemon.data.model.PokemonResposta
import retrofit2.Response

interface IPokemonApiHelper {

    suspend fun getPokemon(): Response<PokemonResposta>


}