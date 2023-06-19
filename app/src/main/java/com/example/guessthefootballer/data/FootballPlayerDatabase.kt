package com.example.guessthefootballer.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FootballPlayer::class], version = 1)
abstract class FootballPlayerDatabase : RoomDatabase() {
    abstract fun footballPlayerDao(): FootballPlayerDao

    companion object {
        fun getDatabase(applicationContext: Context): FootballPlayerDatabase {
            return Room.databaseBuilder(
                applicationContext,
                FootballPlayerDatabase::class.java,
                "football-players"
            ).build();
        }
    }
}