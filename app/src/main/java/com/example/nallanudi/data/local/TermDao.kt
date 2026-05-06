package com.example.nallanudi.data.local

import androidx.room.*

import kotlinx.coroutines.flow.Flow

@Dao
interface TermDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(
        term: FavoriteTermEntity
    )

    @Delete
    suspend fun deleteFavorite(
        term: FavoriteTermEntity
    )

    @Query("SELECT * FROM favorites")
    fun getFavorites():
            Flow<List<FavoriteTermEntity>>
}