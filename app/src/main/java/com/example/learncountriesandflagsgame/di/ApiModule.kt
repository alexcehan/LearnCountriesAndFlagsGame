package com.example.learncountriesandflagsgame.di

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.learncountriesandflagsgame.data.repository.CountriesApiRepositoryImpl
import com.example.learncountriesandflagsgame.data.sources.api.RestCountriesApi
import com.example.learncountriesandflagsgame.data.usecases.FetchDataFromApiUseCase
import com.example.learncountriesandflagsgame.data.usecases.FetchDataFromApiUseCaseImpl
import com.example.learncountriesandflagsgame.domain.repository.CountriesApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {


    private val regionsString: MutableLiveData<List<String>> = MutableLiveData(emptyList())

    @Singleton
    @Provides
    fun provideStringOfRegions(): LiveData<List<String>> {
        return regionsString
    }

    @Singleton
    @Provides
    fun provideStringOfRegionsSetter(): MutableLiveData<List<String>> {
        return regionsString
    }



    @Provides
    @Singleton
    fun provideMyApi(): RestCountriesApi {
        return Retrofit.Builder()
            .baseUrl("https://restcountries.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RestCountriesApi::class.java)
    }


    @Provides
    @Singleton
    fun provideFetchDataFromApiUseCase(repository: CountriesApiRepository): FetchDataFromApiUseCase {
        return FetchDataFromApiUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun provideCountriesApiRepository(api: RestCountriesApi): CountriesApiRepository {
        return CountriesApiRepositoryImpl(api)
    }
}