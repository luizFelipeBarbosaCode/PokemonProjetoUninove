package com.example.applicationpokemon.ui.pokemon.data.model

import java.io.Serializable

data class Pokemon(
    val name: String,
    val url: String,
    val image: Int

) : Serializable
