package com.example.applicationpokemon.ui.mypokemon.presentation

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationpokemon.R
import com.example.applicationpokemon.ui.BaseFragment
import com.example.applicationpokemon.ui.pokemon.presentation.PokemonAdapter
import com.google.android.material.snackbar.Snackbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.my_pokemon_fragment.*

class MyPokemonFragment: BaseFragment(R.layout.my_pokemon_fragment) {

    val TAG = "SAVES_NEWS_FRAGMENT"

    lateinit var podemonAdapter: PokemonAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()


        podemonAdapter.setOnItemClickListener {pokemon ->
            val bundle = Bundle().apply {
                putSerializable("pokemon", pokemon)
            }
            findNavController().navigate(
                R.id.action_savesNewsFragment_to_detailFragment,
                bundle
            )
        }

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean { return true }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val pokemon = podemonAdapter.differ.currentList[position]
                viewModel.deletePokemon(pokemon)
                Snackbar.make(view, "Successfully deleted Pokemon", Snackbar.LENGTH_LONG).apply {
                    setAction("Undo") {
                        viewModel.savePokemon(pokemon)
                    }
                    show()
                }
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(rvSavedPokemon)
        }

        viewModel.getSavedPokemon().observe(viewLifecycleOwner, Observer { pokemon ->
            podemonAdapter.differ.submitList(pokemon)
        })

    }

    private fun setupRecyclerView() {
        podemonAdapter = PokemonAdapter()
        rvSavedPokemon.apply {
            adapter = podemonAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}