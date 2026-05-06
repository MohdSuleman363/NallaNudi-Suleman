package com.example.nallanudi.utils

import android.content.Context
import android.speech.tts.TextToSpeech

import java.util.Locale

class TextToSpeechHelper(
    context: Context
) {

    private var textToSpeech: TextToSpeech? = null

    init {

        textToSpeech = TextToSpeech(
            context
        ) {

            // Initialization
        }
    }

    // English Speech
    fun speakEnglish(text: String) {

        textToSpeech?.language = Locale.US

        textToSpeech?.speak(
            text,
            TextToSpeech.QUEUE_FLUSH,
            null,
            null
        )
    }

    // Kannada Speech
    fun speakKannada(text: String) {

        val kannadaLocale = Locale("kn", "IN")

        textToSpeech?.language = kannadaLocale

        textToSpeech?.speak(
            text,
            TextToSpeech.QUEUE_FLUSH,
            null,
            null
        )
    }

    fun shutdown() {

        textToSpeech?.stop()

        textToSpeech?.shutdown()
    }
}