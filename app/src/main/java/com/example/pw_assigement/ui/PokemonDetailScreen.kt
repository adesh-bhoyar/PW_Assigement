package com.example.pw_assigement.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pw_assigement.R
import com.example.pw_assigement.model.PokemonDetailResponse
import com.example.pw_assigement.ui.theme.PrimaryColor
import com.example.pw_assigement.viewmodel.PokemonViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailScreen(
    pokemonName: String,
    viewModel: PokemonViewModel = viewModel(),
    onBackClick: () -> Unit
) {
    val pokemonDetail by viewModel.pokemonDetail.observeAsState()

    LaunchedEffect(pokemonName) {
        viewModel.fetchPokemonDetail(pokemonName)
    }

    Scaffold(
        topBar = {
            TopAppBar(navigationIcon = {
                IconButton(onClick = { onBackClick() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(R.string.back)
                    )
                }
            }, colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = PrimaryColor,
                titleContentColor = Color.White,
                navigationIconContentColor = Color.White
            ),
                title = { Text(text = pokemonName) }
            )
        }
    ) { paddingValues ->
        PokemonDetailContent(
            pokemonDetail = pokemonDetail,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        )
    }
}

@Composable
fun PokemonDetailContent(
    pokemonDetail: PokemonDetailResponse?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        pokemonDetail?.let { detail ->
            PokemonDetailSection(title = "Abilities") {
                detail.abilities.forEach { abilitySlot ->
                    DetailChip(text = abilitySlot.ability.name.capitalize())
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            PokemonDetailSection(title = "Stats") {
                detail.stats.forEach { stat ->
                    Text(
                        text = "${stat.stat!!.name!!.capitalize()}: ${stat.baseStat}",
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Weight: ${detail.weight} hectograms",
                fontSize = 20.sp,
                color = Color.Black,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun PokemonDetailSection(
    title: String,
    content: @Composable () -> Unit
) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineMedium,
        color = PrimaryColor,
        textAlign = TextAlign.Center
    )
    Spacer(modifier = Modifier.height(8.dp))
    content()
}

@Composable
fun DetailChip(text: String) {
    Surface(
        modifier = Modifier.padding(4.dp),
        shape = MaterialTheme.shapes.small,
        color = PrimaryColor,
        contentColor = Color.White,
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}