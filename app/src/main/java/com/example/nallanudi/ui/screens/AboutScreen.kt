package com.example.nallanudi.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AboutScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Nalla-Nudi",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text =
                "An offline Kannada technical dictionary app built using Kotlin and Jetpack Compose."
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text =
                "Features include search, favorites, pronunciation, and AI-powered explanations."
        )
    }
}