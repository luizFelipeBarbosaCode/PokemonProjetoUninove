package com.example.applicationpokemon.ui.pokemon.presentation

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.provider.ContactsContract
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.applicationpokemon.core.utils.Resource
import com.example.applicationpokemon.ui.GenericRecylerAdapter
import com.example.applicationpokemon.ui.MyApplication
import com.example.applicationpokemon.ui.pokemon.data.model.Pokemon
import com.example.applicationpokemon.ui.pokemon.data.model.PokemonResposta
import com.example.applicationpokemon.ui.pokemon.data.repository.PokemonRepository
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class PokemonViewModel(app: Application, val pokemonRepository: PokemonRepository) : AndroidViewModel(app) {

    val pokemonList: MutableLiveData<Resource<PokemonResposta>> = MutableLiveData()
    var pokemonResposta: PokemonResposta? = null
    var adapter = GenericRecylerAdapter<PokemonResposta>()
    var pokemonListResponse: PokemonResposta? = null
    var value = MutableLiveData<Resource<MutableList<PokemonResposta>>>()

    init {
        getPokemonn()
    }

    fun getPokemonn() = viewModelScope.launch {
        listaPokemons()
    }

    private suspend fun listaPokemons() {
        try {
            if (hasInternetConnection()) {

                val response = pokemonRepository.listaPokemon()
                pokemonList.postValue(getListaPokemons(response))
            } else {
                pokemonList.postValue(Resource.Error("No internet connection"))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> pokemonList.postValue(Resource.Error("Network Failure"))
                else -> pokemonList.postValue(Resource.Error("Conversion Error"))
            }
        }
    }

    private fun getListaPokemons(response: Response<PokemonResposta>) : Resource<PokemonResposta> {

        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->

                if (pokemonResposta == null)
                    pokemonResposta = resultResponse
                else
                {
                    val oldPokemon = pokemonListResponse?.results
                    val pokemon = resultResponse.results
                    oldPokemon?.addAll(pokemon)
                }

                return Resource.Success(pokemonResposta ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<MyApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when (type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ContactsContract.CommonDataKinds.Email.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }

    fun savePokemon(pokemon: Pokemon) = viewModelScope.launch {
        pokemonRepository.upsert(pokemon)
    }

    fun getSavedPokemon() = pokemonRepository.getSavesPokemon()

    fun deletePokemon(pokemon: Pokemon) = viewModelScope.launch {
        pokemonRepository.deletePokemon(pokemon)
    }
}
