package com.example.applicationpokemon.ui.mypokemon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.applicationpokemon.R

class MyPokemonFragment : Fragment() {

    companion object {
        fun newInstance() = MyPokemonFragment()
    }

    private lateinit var viewModel: MyPokemonViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.my_pokemon_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this,).get(MyPokemonViewModel::class.java)

    }

}