package com.example.pw_assigement.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pw_assigement.model.Pokemon
import com.example.pw_assigement.model.PokemonDetailResponse
import com.example.pw_assigement.network.ApiClient
import kotlinx.coroutines.launch

class PokemonViewModel : ViewModel() {
    val pokemonList = MutableLiveData<List<Pokemon>>()
    val pokemonDetail = MutableLiveData<PokemonDetailResponse>()

    init {
        fetchPokemonList()
    }

    fun fetchPokemonList() {
        viewModelScope.launch {
            val response = ApiClient.retrofitService.getPokemonList()
            pokemonList.postValue(response.results)
        }
    }

    fun fetchPokemonDetail(name: String) {
        viewModelScope.launch {
            val response = ApiClient.retrofitService.getPokemonDetail(name)
            pokemonDetail.postValue(response)
        }
    }
}