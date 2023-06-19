package com.example.guessthefootballer.data

import androidx.room.*

@Dao
interface FootballPlayerDao {
    @Query("SELECT name,nationality,league,club,position,age,kitnum FROM footballplayer ORDER BY id ASC")
    fun getAll(): List<FootballPlayer>

    @Query("SELECT name FROM footballplayer ORDER BY id ASC")
    fun getAllNames(): List<String>

    @Insert
    fun insert(footballPlayer: FootballPlayer): Long

    @Update
    fun update(footballPlayer: FootballPlayer)

    @Delete
    fun deleteItem(footballPlayer: FootballPlayer)
}