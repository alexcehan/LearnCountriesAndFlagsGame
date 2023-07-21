package com.example.learncountriesandflagsgame.data.sources.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.learncountriesandflagsgame.data.entities.*

@Database(entities = [Countries::class, OneLifeStatsCapital::class, OneLifeStatsFlags::class, ThreeLivesStatsCapital::class, ThreeLivesStatsFlags::class], version = 1, exportSchema = false)
abstract class CountriesAndFlagsLocalDatabase:RoomDatabase() {

    abstract val countriesAndFlagsDao: CountriesAndFlagsDao

    companion object {

        @Volatile
        private var INSTANCE: CountriesAndFlagsLocalDatabase? = null

        fun getInstance(context: Context): CountriesAndFlagsLocalDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if(instance==null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CountriesAndFlagsLocalDatabase::class.java,
                        "countries_database").build()

                    INSTANCE = instance
                }

                return instance
            }

        }
    }
}