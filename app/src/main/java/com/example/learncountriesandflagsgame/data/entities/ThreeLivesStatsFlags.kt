package com.example.learncountriesandflagsgame.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "three_lives_stats_flags")
data class ThreeLivesStatsFlags(

    @PrimaryKey(autoGenerate = true)
    override var id: Long = 0L,

    override var user: String,

    override var score: Int
): RankItem(id, user, score)
