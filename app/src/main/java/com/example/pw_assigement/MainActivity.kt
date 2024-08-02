package com.example.pw_assigement

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.pw_assigement.navigation.Navigation
import com.example.pw_assigement.viewmodel.PokemonViewModel

class MainActivity : ComponentActivity() {

    private val pokemonViewModel: PokemonViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation(viewModel = pokemonViewModel)
        }
    }
}
