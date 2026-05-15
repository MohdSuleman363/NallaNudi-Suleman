package com.example.nallanudi.data.repository

import com.example.nallanudi.data.model.Term

object TermRepository {

    fun getTerms(): List<Term> {

        return listOf(

            Term(
                english = "Velocity",
                kannada = "ವೇಗ",
                subject = "Science",
                definition = "Speed in a specific direction",
                explanation = "Velocity tells how fast something moves in a direction.",
                example = "The car moved with high velocity."
            ),

            Term(
                english = "Gravity",
                kannada = "ಗುರುತ್ವಾಕರ್ಷಣೆ",
                subject = "Science",
                definition = "Force that attracts objects",
                explanation = "Gravity pulls objects toward Earth.",
                example = "Gravity keeps us on the ground."
            ),

            Term(
                english = "Energy",
                kannada = "ಶಕ್ತಿ",
                subject = "Science",
                definition = "Ability to do work",
                explanation = "Energy is needed for movement and activity.",
                example = "Food gives us energy."
            ),

            Term(
                english = "Algorithm",
                kannada = "ಅಲ್ಗಾರಿದಮ್",
                subject = "General",
                definition = "Step-by-step procedure",
                explanation = "Algorithms are used to solve problems.",
                example = "Sorting numbers uses an algorithm."
            ),

            Term(
                english = "Photosynthesis",
                kannada = "ಪ್ರಕಾಶಸಂಶ್ಲೇಷಣೆ",
                subject = "Science",
                definition = "Process by which plants make food",
                explanation = "Plants use sunlight to produce food.",
                example = "Photosynthesis occurs in green plants."
            ),

            Term(
                english = "Addition",
                kannada = "ಸೇರಿಕೆ",
                subject = "Math",
                definition = "Process of adding numbers",
                explanation = "Addition combines two or more numbers.",
                example = "2 + 3 is an addition problem."
            ),

            Term(
                english = "Triangle",
                kannada = "ತ್ರಿಭುಜ",
                subject = "Math",
                definition = "Shape with three sides",
                explanation = "Triangles are basic geometric shapes.",
                example = "An equilateral triangle has equal sides."
            ),

            Term(
                english = "Computer",
                kannada = "ಗಣಕಯಂತ್ರ",
                subject = "General",
                definition = "Electronic machine for computing",
                explanation = "Computers process and store information.",
                example = "I use a computer for coding."
            )

        )
    }
}