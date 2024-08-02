package com.example.pw_assigement.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.pw_assigement.ui.theme.PrimaryColor

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddNoteScreen(navController: NavController, viewModel: ViewModel, onBackClick: () -> Unit) {
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Add Note") },
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = PrimaryColor,
                titleContentColor = Color.White,
                navigationIconContentColor = Color.White
            ),
            navigationIcon = {
                IconButton(onClick = { onBackClick() }) { // Ensure this is a function call
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            })
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize() // Ensure the column takes full height
                .padding(paddingValues) // Add padding to avoid overlap with TopAppBar
                .padding(16.dp)
        ) {
            var textTitle by remember { mutableStateOf("") }
            var isErrorTitle by remember { mutableStateOf(false) }

            OutlinedTextField(value = textTitle,
                onValueChange = {
                    textTitle = it
                    isErrorTitle = textTitle.length < 3
                },
                label = { Text("Title") },
                isError = isErrorTitle,
                modifier = Modifier.fillMaxWidth()
            )

            if (isErrorTitle) {
                Text(
                    text = "Title must be at least 3 characters long",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.labelSmall
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            var textDesc by remember { mutableStateOf("") }
            var isErrorDesc by remember { mutableStateOf(false) }

            OutlinedTextField(value = textDesc,
                onValueChange = {
                    textDesc = it
                    isErrorDesc = textDesc.length < 3
                },
                label = { Text("Description") },
                isError = isErrorDesc,
                modifier = Modifier.fillMaxWidth()
            )

            if (isErrorDesc) {
                Text(
                    text = "Description must be at least 3 characters long",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.labelSmall
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (textTitle.length >= 3 && textDesc.length >= 3) {
                        // Handle successful input submission
                        // For example: viewModel.saveNote(textTitle, textDesc)
                    } else {
                        isErrorTitle = textTitle.length < 3
                        isErrorDesc = textDesc.length < 3
                    }
                }, modifier = Modifier.fillMaxWidth()
            ) {
                Text("Submit")
            }
        }
    }
}
