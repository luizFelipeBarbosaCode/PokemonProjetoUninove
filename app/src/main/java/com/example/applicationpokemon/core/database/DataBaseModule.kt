package com.example.applicationpokemon.core.database

import android.content.Context
import androidx.room.Room
import com.example.applicationpokemon.ui.pokemondetail.data.db.IPokemonDetailsDaoHelper
import com.example.applicationpokemon.ui.pokemondetail.data.db.PokemonDetailHelperImpl
import com.example.applicationpokemon.ui.pokemondetail.data.db.PokemonDetailsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DataBaseModule {
    @Singleton
    @Provides
    fun providePokemonDetailDatabase(
        @ApplicationContext context: Context
    ) =
        Room.databaseBuilder(
            context.applicationContext,
            PokemonDetailsDatabase::class.java,
            "pokemon_db.db"
        ).build()

    @Singleton
    @Provides
    fun providePokemonDetailDao(pokemonDetailDatabase: PokemonDetailsDatabase) =
        pokemonDetailDatabase.getPokemonDetailsDao()

    @Singleton
    @Provides
    fun providePokemonDetailHelper(pokemonDetailDaoHelperImpl: PokemonDetailHelperImpl)
            : IPokemonDetailsDaoHelper = pokemonDetailDaoHelperImpl


}
