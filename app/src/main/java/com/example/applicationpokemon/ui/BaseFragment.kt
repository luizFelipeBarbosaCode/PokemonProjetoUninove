package com.example.applicationpokemon.ui

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.example.applicationpokemon.ui.pokemon.presentation.PokemonViewModel

open class BaseFragment (@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {

    lateinit var viewModel: PokemonViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

    }

}