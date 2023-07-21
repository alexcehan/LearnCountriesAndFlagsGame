package com.example.learncountriesandflagsgame.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "one_life_stats_capital")
data class OneLifeStatsCapital(

    @PrimaryKey(autoGenerate = true)
    var id: Long,

    var user: String,

    var score: Int
)
