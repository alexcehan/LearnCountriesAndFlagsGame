package com.alexsapps.learncountriesandflagsgame.data.sources.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alexsapps.learncountriesandflagsgame.data.entities.*

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

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllCountries(countriesList: List<Countries>)

    @Query("SELECT * FROM countries")
    fun getAllCountries(): LiveData<List<Countries>>

    @Query("SELECT COUNT(*) FROM countries")
    fun getTheNumberOfCountriesInDb() : LiveData<Int>

    @Query("SELECT * FROM countries WHERE regionName IN (:stringOfRegions)")
    fun getCountriesFromSelectedRegions(stringOfRegions: List<String>): LiveData<List<Countries>>

    @Query("SELECT * FROM countries WHERE countryId > 20")
    fun getCountriesFromSelectedRegionsWithId(): LiveData<List<Countries>>

    @Query("select (select count(*) from three_lives_stats_capital where score >=:score) + 0 AS position")
    fun getTheRankOnThreeLivesCapitalBasedOnCurrentScore(score: Int): LiveData<Int>

    @Query("select (select count(*) from one_life_stats_capital where score >=:score) + 0 AS position")
    fun getTheRankOnOneLifeCapitalBasedOnCurrentScore(score: Int): LiveData<Int>

    @Query("select (select count(*) from three_lives_stats_flags where score >=:score) + 0 AS position")
    fun getTheRankOnThreeLivesFlagsBasedOnCurrentScore(score: Int): LiveData<Int>

    @Query("select (select count(*) from one_life_stats_flags where score >=:score) + 0 AS position")
    fun getTheRankOnOneLifeFlagBasedOnCurrentScore(score: Int): LiveData<Int>

    @Query("SELECT * FROM one_life_stats_capital ORDER BY score DESC LIMIT 5")
    fun getTopFiveRankPositionsFromOneLifeCapitalTable(): LiveData<List<RankItem>>

    @Query("SELECT * FROM three_lives_stats_capital ORDER BY score DESC LIMIT 5")
    fun getTopFiveRankPositionsFromThreeLivesCapitalTable(): LiveData<List<RankItem>>

    @Query("SELECT * FROM one_life_stats_flags ORDER BY score DESC LIMIT 5")
    fun getTopFiveRankPositionsFromOneLifeFlagsTable(): LiveData<List<RankItem>>

    @Query("SELECT * FROM three_lives_stats_flags ORDER BY score DESC LIMIT 5")
    fun getTopFiveRankPositionsFromThreeLivesFlagsTable(): LiveData<List<RankItem>>

}