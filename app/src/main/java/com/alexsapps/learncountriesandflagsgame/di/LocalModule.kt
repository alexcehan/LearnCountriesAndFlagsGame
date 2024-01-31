package com.alexsapps.learncountriesandflagsgame.di

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    private val regionsString: MutableLiveData<List<String>> = MutableLiveData(emptyList())
    private var typeOfGame: MutableLiveData<String> = MutableLiveData("")
    private val nameOfTheGame: MutableLiveData<Boolean> = MutableLiveData(true)

    @Singleton
    @Provides
    fun providesTypeOfGame() : LiveData<String> {
        return typeOfGame
    }

    @Singleton
    @Provides
    fun provideStringOfRegions(): LiveData<List<String>> {
        return regionsString
    }

    @Singleton
    @Provides
    fun providesNameOfTheGame(): LiveData<Boolean> {
        return nameOfTheGame
    }

    @Singleton
    @Provides
    fun provideTypeOfGameSetter(): MutableLiveData<String> {
        return typeOfGame
    }

    @Singleton
    @Provides
    fun provideStringOfRegionsSetter(): MutableLiveData<List<String>> {
        return regionsString
    }

    @Singleton
    @Provides
    fun provideNameOfTheGameSetter(): MutableLiveData<Boolean>{
        return nameOfTheGame
    }

}