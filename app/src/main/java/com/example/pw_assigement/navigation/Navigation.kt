package com.example.pw_assigement.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pw_assigement.ui.AddNoteScreen
import com.example.pw_assigement.ui.PokemonDetailScreen
import com.example.pw_assigement.ui.PokemonListScreen
import com.example.pw_assigement.viewmodel.PokemonViewModel

@Composable
fun Navigation(viewModel: PokemonViewModel) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "pokemon_list") {
        composable(route = "pokemon_list") {
            val viewModel: PokemonViewModel = viewModel()
            PokemonListScreen(navController = navController, viewModel = viewModel)
        }

        composable(
            route = "pokemon_detail" + "/{pokemon}",
            arguments = listOf(navArgument("pokemon") {
                type = NavType.StringType
                defaultValue = "Pikachu"
                nullable = true
            })
        ) { entry ->
            entry.arguments?.getString("pokemon")?.let {
                PokemonDetailScreen(pokemonName = it, viewModel = viewModel,
                    onBackClick = { navController.popBackStack() })
            }
        }
        composable(route = "add_note") {
            val viewModel: PokemonViewModel = viewModel()
            AddNoteScreen(
                navController = navController,
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
