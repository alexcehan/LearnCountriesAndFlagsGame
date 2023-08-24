package com.example.learncountriesandflagsgame.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "one_life_stats_capital")
data class OneLifeStatsCapital(

    @PrimaryKey(autoGenerate = true)
    override var id: Long,

    override var user: String,

    override var score: Int
): RankItem(id, user, score)
