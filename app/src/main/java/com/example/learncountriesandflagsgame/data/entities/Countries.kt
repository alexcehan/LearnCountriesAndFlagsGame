package com.example.learncountriesandflagsgame.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries")
data class Countries(

    @PrimaryKey(autoGenerate = true)
    var countryId: Long = 0L,

    var countryName: String,

    var capitalName: String,

    var regionName: String,

    var flagUrl: String
) {

    override fun equals(other: Any?): Boolean {
        return (other is Countries && countryId==other.countryId)
    }
}