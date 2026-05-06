package com.example.nallanudi.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteTermEntity(

    @PrimaryKey
    val english: String,

    val kannada: String,

    val definition: String,

    val explanation: String,

    val example: String,

    val subject: String
)