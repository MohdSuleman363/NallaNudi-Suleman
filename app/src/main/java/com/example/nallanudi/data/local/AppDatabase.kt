package com.example.nallanudi.data.local

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [FavoriteTermEntity::class],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun termDao(): TermDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(
            context: Context
        ): AppDatabase {

            return INSTANCE ?: synchronized(this) {

                val instance =
                    Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "nalla_nudi_db"
                    ).build()

                INSTANCE = instance

                instance
            }
        }
    }
}