package com.example.nallanudi.data.repository

import com.example.nallanudi.data.model.Term

object TermRepository {

    val terms = listOf(

        Term(
            english = "Photosynthesis",
            kannada = "ಪ್ರಕಾಶಸಂಶ್ಲೇಷಣೆ",
            definition = "Plant food process",
            explanation =
                "Plants use sunlight water and carbon dioxide to make food",

            example =
                "Plants perform photosynthesis during daylight",

            subject = "Science"
        ),

        Term(
            english = "Algorithm",
            kannada = "ಅಲ್ಗಾರಿದಮ್",

            definition =
                "Step by step procedure",

            explanation =
                "A sequence of instructions used to solve problems",

            example =
                "Sorting numbers uses an algorithm",

            subject = "Math"
        ),

        Term(
            english = "Velocity",
            kannada = "ವೇಗ",

            definition =
                "Speed with direction",

            explanation =
                "Velocity includes both speed and direction",

            example =
                "The car moved north at sixty kilometer per hour",

            subject = "Science"
        ),

        Term(
            english = "Theorem",
            kannada = "ಸಿದ್ಧಾಂತ",

            definition =
                "A mathematically proven statement",

            explanation =
                "Theorems are proven using logical rules",

            example =
                "Pythagoras theorem is widely used",

            subject = "Math"
        )
    )
}