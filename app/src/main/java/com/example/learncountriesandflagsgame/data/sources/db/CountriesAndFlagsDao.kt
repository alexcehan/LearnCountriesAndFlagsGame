package com.example.learncountriesandflagsgame.data.sources.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.learncountriesandflagsgame.data.entities.*

@Dao
interface CountriesAndFlagsDao {

    @Insert
    suspend fun insert(countries: Countries)

    @Insert
    suspend fun insert(oneLifeStatsFlags: OneLifeStatsFlags)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(oneLifeStatsCapital: OneLifeStatsCapital)

    @Insert
    suspend fun insert(threeLivesStatsFlags: ThreeLivesStatsFlags)

    @Insert
    suspend fun insert(threeLivesStatsCapital: ThreeLivesStatsCapital)

    @Insert
    suspend fun insertAllCountries(countriesList: List<Countries>)

    @Query("SELECT * FROM countries")
    fun getAllCountries(): LiveData<List<Countries>>

    @Query("SELECT * FROM countries WHERE regionName IN (:stringOfRegions)")
    fun getCountriesFromSelectedRegions(stringOfRegions: List<String>): LiveData<List<Countries>>

    @Query("SELECT * FROM countries WHERE countryId > 20")
    fun getCountriesFromSelectedRegionsWithId(): LiveData<List<Countries>>

}