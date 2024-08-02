package com.example.pw_assigement.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pw_assigement.R
import com.example.pw_assigement.model.Pokemon
import com.example.pw_assigement.ui.theme.PrimaryColor
import com.example.pw_assigement.viewmodel.PokemonViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonListScreen(navController: NavController, viewModel: PokemonViewModel) {
    val pokemonList by viewModel.pokemonList.observeAsState(emptyList())

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "My Pokemons") },
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = PrimaryColor, titleContentColor = Color.White
            ),
            actions = {
                Icon(Icons.Default.Favorite,
                    contentDescription = "",
                    modifier = Modifier
                        .clickable { }
                        .padding(6.dp)
                        .size(32.dp))
            },
        )
    }, floatingActionButton = {
        FloatingActionButton(onClick = { navController.navigate("add_note") }) {
            Icon(Icons.Filled.Add, "")
        }
    }) {
        Column {
            LazyColumn {
                items(pokemonList) { pokemon ->
                    PokemonListItem(pokemon, onClick = {
                        navController.navigate("pokemon_detail/${pokemon.name}")
                    })
                }
            }
        }
    }
}

@Composable
fun PokemonListItem(pokemon: Pokemon, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = pokemon.name!!.capitalize(),
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun CarouselDemo() {
    val items = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")
    Carousel(items = items)
}

@Composable
fun Carousel(items: List<String>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(items.size) { index ->
            CarouselItem(itemText = items[index])
        }
    }
}


@Composable
fun CarouselItem(itemText: String) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .size(200.dp, 150.dp)
            .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(text = itemText, color = Color.White, style = MaterialTheme.typography.headlineMedium)
    }
}
