package com.example.nallanudi.viewmodel

import android.app.Application
import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import com.example.nallanudi.data.model.Term

class TermViewModel(application: Application) : AndroidViewModel(application) {

    private val prefs =
        application.getSharedPreferences(
            "favorites",
            Context.MODE_PRIVATE
        )

    private val _favorites =
        mutableStateListOf<String>()

    val favorites: List<String>
        get() = _favorites

    init {

        val saved =
            prefs.getStringSet(
                "saved_terms",
                emptySet()
            ) ?: emptySet()

        _favorites.addAll(saved)
    }

    fun toggleFavorite(term: Term) {

        if (_favorites.contains(term.english)) {

            _favorites.remove(term.english)

        } else {

            _favorites.add(term.english)
        }

        saveFavorites()
    }

    fun isFavorite(term: Term): Boolean {

        return _favorites.contains(term.english)
    }

    private fun saveFavorites() {

        prefs.edit()
            .putStringSet(
                "saved_terms",
                _favorites.toSet()
            )
            .apply()
    }
}