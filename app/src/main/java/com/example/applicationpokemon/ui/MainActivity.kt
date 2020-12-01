package com.example.applicationpokemon.ui

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.applicationpokemon.R
import com.example.applicationpokemon.ui.pokemon.data.repository.PokemonRepository
import com.example.applicationpokemon.ui.pokemon.presentation.PokemonViewModel
import com.example.applicationpokemon.ui.pokemon.presentation.PokemonViewModelProviderFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var viewModel: PokemonViewModel

    @Inject
    lateinit var repository: PokemonRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        IntentFilter(Intent.ACTION_PROCESS_TEXT)

        val viewmodelProviderFactory = PokemonViewModelProviderFactory(application, repository)
        viewModel = ViewModelProvider(this, viewmodelProviderFactory).get(PokemonViewModel::class.java)

        bottom_nav.setItemIconTintList(null);
        bottom_nav.setupWithNavController((nav_host_fragment.findNavController()))

    }
}