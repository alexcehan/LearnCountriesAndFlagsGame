package com.alexsapps.learncountriesandflagsgame.di

import com.alexsapps.learncountriesandflagsgame.data.repository.CountriesApiRepositoryImpl
import com.alexsapps.learncountriesandflagsgame.data.sources.api.RestCountriesApi
import com.alexsapps.learncountriesandflagsgame.data.usecases.FetchDataFromApiUseCase
import com.alexsapps.learncountriesandflagsgame.data.usecases.FetchDataFromApiUseCaseImpl
import com.alexsapps.learncountriesandflagsgame.domain.repository.CountriesApiRepository
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