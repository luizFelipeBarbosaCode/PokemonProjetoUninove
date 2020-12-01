package com.example.applicationpokemon.ui.pokemondetail.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.applicationpokemon.ui.pokemon.data.model.Pokemon

@Dao
interface PokemonDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(pokemon: Pokemon): Long

    @Query("SELECT * FROM pokemon")
    fun getAllPokemon() : LiveData<List<Pokemon>>

    @Delete
    suspend fun deletePokemon(pokemon: Pokemon)
}