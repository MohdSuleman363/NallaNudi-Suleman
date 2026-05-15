package com.example.nallanudi.ui.screens

import android.speech.tts.TextToSpeech
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import java.util.*

@Composable
fun DetailScreen(
    english: String,
    kannada: String,
    definition: String,
    explanation: String,
    example: String
) {

    val context = LocalContext.current

    val tts = remember {

        TextToSpeech(context) {}
    }

    DisposableEffect(Unit) {

        onDispose {
            tts.stop()
            tts.shutdown()
        }
    }

    Column(

        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFFF8F4FF),
                        Color.White
                    )
                )
            )
            .verticalScroll(rememberScrollState())
            .padding(20.dp)

    ) {

        Card(

            shape = RoundedCornerShape(28.dp),

            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),

            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ),

            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(24.dp)
            ) {

                Column {

                    Text(
                        text = english,
                        fontSize = 34.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Spacer(
                        modifier = Modifier.height(6.dp)
                    )

                    Text(
                        text = kannada,
                        fontSize = 24.sp,
                        color = Color(0xFF6A1B9A),
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        Button(

                            onClick = {

                                tts.language = Locale.US

                                tts.speak(
                                    english,
                                    TextToSpeech.QUEUE_FLUSH,
                                    null,
                                    null
                                )
                            },

                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFE1BEE7)
                            ),

                            shape = RoundedCornerShape(18.dp)
                        ) {

                            Text(
                                text = "🔊 English",
                                color = Color.Black
                            )
                        }

                        Button(

                            onClick = {

                                tts.language = Locale("kn")

                                tts.speak(
                                    kannada,
                                    TextToSpeech.QUEUE_FLUSH,
                                    null,
                                    null
                                )
                            },

                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFD1C4E9)
                            ),

                            shape = RoundedCornerShape(18.dp)
                        ) {

                            Text(
                                text = "🔊 Kannada",
                                color = Color.Black
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                ModernSectionCard(
                    title = "Definition",
                    content = definition
                )

                Spacer(modifier = Modifier.height(16.dp))

                ModernSectionCard(
                    title = "Explanation",
                    content = explanation
                )

                Spacer(modifier = Modifier.height(16.dp))

                ModernSectionCard(
                    title = "Example",
                    content = example
                )
            }
        }
    }
}

@Composable
fun ModernSectionCard(
    title: String,
    content: String
) {

    Card(

        shape = RoundedCornerShape(22.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF3E5F5)
        )

    ) {

        Column(
            modifier = Modifier.padding(18.dp)
        ) {

            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF6A1B9A)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = content,
                fontSize = 16.sp,
                color = Color.DarkGray,
                lineHeight = 24.sp
            )
        }
    }
}