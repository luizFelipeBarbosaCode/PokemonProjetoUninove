package com.example.applicationpokemon.ui.pokemon.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "pokemon"
)
data class Pokemon(
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null,
    val name: String,
    val url: String,
    var image: String

) : Serializable
