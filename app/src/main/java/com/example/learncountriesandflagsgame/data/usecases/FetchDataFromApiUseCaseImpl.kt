package com.example.learncountriesandflagsgame.data.usecases

import com.example.learncountriesandflagsgame.data.pojos.ApiJsonResult
import com.example.learncountriesandflagsgame.domain.repository.CountriesApiRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchDataFromApiUseCaseImpl @Inject constructor(private val repository: CountriesApiRepository) : FetchDataFromApiUseCase {

    override suspend fun invoke(): Flow<ApiJsonResult> {
        return repository.fetchAllCountriesData()
    }
}