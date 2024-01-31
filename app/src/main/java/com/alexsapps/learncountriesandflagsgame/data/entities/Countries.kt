package com.alexsapps.learncountriesandflagsgame.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "countries", indices = [Index(value = ["countryName"], unique = true)])
data class Countries(

    @PrimaryKey(autoGenerate = true)
    var countryId: Long = 0L,

    @ColumnInfo(name = "countryName")
    var countryName: String,

    var capitalName: String,

    var regionName: String,

    var flagUrl: String
) {

    override fun equals(other: Any?): Boolean {
        return (other is Countries && countryId==other.countryId)
    }
}