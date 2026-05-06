package com.example.nallanudi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.nallanudi.data.local.FavoriteTermEntity
import com.example.nallanudi.data.local.TermDao

import com.example.nallanudi.data.model.Term
import com.example.nallanudi.data.repository.TermRepository

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TermViewModel(
    private val dao: TermDao
) : ViewModel() {

    private val allTerms =
        TermRepository.terms

    // Search
    private val _query =
        MutableStateFlow("")

    val query: StateFlow<String> =
        _query

    // Subject
    private val _selectedSubject =
        MutableStateFlow("All")

    val selectedSubject:
            StateFlow<String> =
        _selectedSubject

    // Favorites from Room
    val favorites =
        dao.getFavorites()
            .map { entityList ->

                entityList.map {

                    Term(
                        english = it.english,
                        kannada = it.kannada,
                        definition = it.definition,
                        explanation = it.explanation,
                        example = it.example,
                        subject = it.subject
                    )
                }
            }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                emptyList()
            )

    // Filtered Terms
    val filteredTerms =
        combine(
            query,
            selectedSubject
        ) { queryText, subject ->

            allTerms.filter { term ->

                val matchesQuery =
                    term.english.contains(
                        queryText,
                        ignoreCase = true
                    )

                val matchesSubject =
                    subject == "All" ||
                            term.subject == subject

                matchesQuery &&
                        matchesSubject
            }
        }

    fun updateQuery(
        newQuery: String
    ) {

        _query.value = newQuery
    }

    fun updateSubject(
        subject: String
    ) {

        _selectedSubject.value =
            subject
    }

    fun toggleFavorite(
        term: Term
    ) {

        viewModelScope.launch {

            val entity =
                FavoriteTermEntity(
                    english = term.english,
                    kannada = term.kannada,
                    definition = term.definition,
                    explanation = term.explanation,
                    example = term.example,
                    subject = term.subject
                )

            if (
                favorites.value.contains(term)
            ) {

                dao.deleteFavorite(entity)

            } else {

                dao.insertFavorite(entity)
            }
        }
    }

    fun isFavorite(
        term: Term
    ): Boolean {

        return favorites.value.contains(term)
    }
}