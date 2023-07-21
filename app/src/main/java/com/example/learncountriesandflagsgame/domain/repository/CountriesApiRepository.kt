package com.example.learncountriesandflagsgame.domain.repository

import com.example.learncountriesandflagsgame.data.pojos.ApiJsonResult
import kotlinx.coroutines.flow.Flow

interface CountriesApiRepository {

    suspend fun fetchAllCountriesData(): Flow<ApiJsonResult>
}