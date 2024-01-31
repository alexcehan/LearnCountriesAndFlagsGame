package com.alexsapps.learncountriesandflagsgame.domain.repository

import com.alexsapps.learncountriesandflagsgame.data.pojos.ApiJsonResult
import kotlinx.coroutines.flow.Flow

interface CountriesApiRepository {

    suspend fun fetchAllCountriesData(): Flow<ApiJsonResult>
}