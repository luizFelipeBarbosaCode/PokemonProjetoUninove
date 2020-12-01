package com.example.applicationpokemon.ui.pokemondetail.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.applicationpokemon.ui.pokemon.data.model.Pokemon

@Database(
    entities = [Pokemon::class],
    version = 1
)

@TypeConverters(Converters::class)
abstract class PokemonDetailsDatabase : RoomDatabase(){

    abstract fun getPokemonDetailsDao() : PokemonDetailDao

}