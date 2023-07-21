package com.example.learncountriesandflagsgame.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "three_lives_stats_flags")
data class ThreeLivesStatsFlags(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    var user: String,

    var score: Int
)
