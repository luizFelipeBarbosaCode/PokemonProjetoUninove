package com.example.applicationpokemon.ui.pokemon.data.datasource

import com.example.applicationpokemon.ui.pokemon.data.model.PokemonResposta
import retrofit2.Response
import javax.inject.Inject

class PokemonApiHelperImpl   @Inject constructor(private val apiService: IPokemonApiService) :
    IPokemonApiHelper
{
    override suspend fun getPokemon(): Response<PokemonResposta> = apiService.obterListaPokemon()
}