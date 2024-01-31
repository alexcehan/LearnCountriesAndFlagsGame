package com.alexsapps.learncountriesandflagsgame.data.repository

import com.alexsapps.learncountriesandflagsgame.data.pojos.ApiJsonResult
import com.alexsapps.learncountriesandflagsgame.data.sources.api.RestCountriesApi
import com.alexsapps.learncountriesandflagsgame.domain.repository.CountriesApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CountriesApiRepositoryImpl @Inject constructor(private val api: RestCountriesApi): CountriesApiRepository {

    override suspend fun fetchAllCountriesData(): Flow<ApiJsonResult> {
        return flow {
            val response = api.getAllCountriesDetails()

            if (response.isSuccessful) {
                val data = response.body()

                emit(data!!)

            } else  {
                throw Exception("There was a problem with your request! Error code: ${response.code()}")
            }
        }
    }
}