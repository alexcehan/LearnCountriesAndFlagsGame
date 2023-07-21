package com.example.learncountriesandflagsgame.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "one_life_stats_flags")
data class OneLifeStatsFlags(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    var user: String,

    var score: Int
)
