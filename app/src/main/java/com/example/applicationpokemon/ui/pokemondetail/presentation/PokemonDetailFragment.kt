package com.example.applicationpokemon.ui.pokemondetail.presentation

import android.os.Bundle
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.applicationpokemon.R
import com.example.applicationpokemon.core.utils.Constrants
import com.example.applicationpokemon.ui.BaseFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.pokemon_detail_fragment.*

class PokemonDetailFragment  : BaseFragment(R.layout.pokemon_detail_fragment)
{

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val args: PokemonDetailFragmentArgs by navArgs()
        var pokemon = args.pokemon

        val urlCortada = pokemon.url.substring(34, pokemon.url.length - 1)
        val image = "${Constrants.URL_IMG}$urlCortada.png"

        pokemon_detail_fragment_img.load(image) {
            crossfade(true)
            crossfade(500)
        }

        pokemon_detail_fragment_txt_name.text = pokemon.name.toUpperCase()

        fab.setOnClickListener {
            pokemon.image = image
            viewModel.savePokemon(pokemon)
            view?.let { it1 -> Snackbar.make(it1, "Pokemon salvo com sucesso", Snackbar.LENGTH_SHORT).show() }
        }
    }

}