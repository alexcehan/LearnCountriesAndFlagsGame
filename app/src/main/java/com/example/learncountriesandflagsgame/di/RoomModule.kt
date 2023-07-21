package com.example.learncountriesandflagsgame.di

import android.content.Context
import androidx.room.Room
import com.example.learncountriesandflagsgame.data.sources.db.CountriesAndFlagsDao
import com.example.learncountriesandflagsgame.data.sources.db.CountriesAndFlagsLocalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): CountriesAndFlagsLocalDatabase {
        return Room.databaseBuilder(context, CountriesAndFlagsLocalDatabase::class.java, "countries_database").build()
    }

    @Provides
    @Singleton
    fun provideCountriesDao(countriesAndFlagsLocalDatabase: CountriesAndFlagsLocalDatabase): CountriesAndFlagsDao {
        return countriesAndFlagsLocalDatabase.countriesAndFlagsDao
    }
}