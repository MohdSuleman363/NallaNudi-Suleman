package com.example.nallanudi.ui.screens

import androidx.compose.foundation.layout.*

import androidx.compose.material3.*

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

import com.example.nallanudi.utils.TextToSpeechHelper

@Composable
fun DetailScreen(

    english: String,

    kannada: String,

    definition: String,

    explanation: String,

    example: String
) {

    val context = LocalContext.current

    val textToSpeechHelper = remember {

        TextToSpeechHelper(context)
    }

    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)

    ) {

        Text(
            text = english,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = kannada,
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(

            horizontalArrangement =
                Arrangement.spacedBy(12.dp)

        ) {

            Button(

                onClick = {

                    textToSpeechHelper
                        .speakEnglish(english)
                }

            ) {

                Text("🔊 English")
            }

            Button(

                onClick = {

                    textToSpeechHelper
                        .speakKannada(kannada)
                }

            ) {

                Text("🔊 Kannada")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Definition",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = definition
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Explanation",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = explanation
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Example",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = example
        )
    }
}