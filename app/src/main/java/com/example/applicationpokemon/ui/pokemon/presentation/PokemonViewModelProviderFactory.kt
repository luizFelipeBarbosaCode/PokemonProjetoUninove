package com.example.applicationpokemon.ui.pokemon.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.applicationpokemon.ui.pokemon.data.repository.PokemonRepository

class PokemonViewModelProviderFactory(val app: Application, private val pokemonRepository: PokemonRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PokemonViewModel(app, pokemonRepository) as T
    }
}