package com.alexsapps.learncountriesandflagsgame.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "one_life_stats_flags")
data class OneLifeStatsFlags(
    @PrimaryKey(autoGenerate = true)
    override var id: Long = 0L,

    override var user: String,

    override var score: Int
): RankItem(id, user, score)
