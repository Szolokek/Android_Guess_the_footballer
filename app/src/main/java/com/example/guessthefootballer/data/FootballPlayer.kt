package com.example.guessthefootballer.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "footballplayer")
data class FootballPlayer(
    @ColumnInfo(name = "id") @PrimaryKey (autoGenerate = true) var id: Long? = null,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "nationality") var nationality: String,
    @ColumnInfo(name = "league") var league: String,
    @ColumnInfo(name = "club") var club: String,
    @ColumnInfo(name = "position") var position: String,
    @ColumnInfo(name = "age") var age: Int,
    @ColumnInfo(name = "kitnum") var kitnum: Int,
)