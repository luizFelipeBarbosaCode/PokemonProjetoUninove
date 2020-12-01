package com.example.applicationpokemon.ui.pokemon.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.applicationpokemon.R
import com.example.applicationpokemon.core.utils.Resource
import com.example.applicationpokemon.ui.BaseFragment
import kotlinx.android.synthetic.main.pokemon_fragment.*

class PokemonFragment : BaseFragment(R.layout.pokemon_fragment) {

    val TAG = "POKEDEX"
    lateinit var pokemonAdapter: PokemonAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        pokemonAdapter.setOnItemClickListener {pokemon ->
            val bundle = Bundle().apply {
                putSerializable("pokemon", pokemon)
            }
            findNavController().navigate(
                R.id.action_pokemonFragment_to_detailPokemonFragment,
                bundle
            )
        }


        viewModel.pokemonList.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let { pokemonResponse ->
                        pokemonAdapter.differ.submitList(pokemonResponse.results)

                    }
                }
                is Resource.Error -> {
                    response.message?.let { message ->
                        Log.e(TAG, "An error occured: $message")
                        Toast.makeText(activity, "An error occured: $message", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        })

    }

    private fun setupRecyclerView() {
        pokemonAdapter = PokemonAdapter()
        pokemon_fragment_recycler_view.apply {
            adapter = pokemonAdapter
            layoutManager = GridLayoutManager(activity, 2)

        }
    }

//    override fun cellForPosition(
//        adapter: GenericRecylerAdapter<*>,
//        cell: RecyclerView.ViewHolder,
//        position: Int
//    ) {
//        (cell as? PokemonCell)?.let {
//            val pokemon: Pokemon = adapter.items[position] as Pokemon
//            cell.set(
//                        pokemon.name.toString()
//                    )
//        }
//    }
//
//    override fun cellType(
//        adapter: GenericRecylerAdapter<*>,
//        position: Int
//    ): GenericRecylerAdapter.AdapterHolderType? {
//        return GenericRecylerAdapter.AdapterHolderType(
//            PokemonCell.resID,
//            PokemonCell::class.java,
//        0
//        )
//    }

}