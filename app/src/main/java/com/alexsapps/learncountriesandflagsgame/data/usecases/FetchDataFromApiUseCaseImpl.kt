package com.alexsapps.learncountriesandflagsgame.data.usecases

import com.alexsapps.learncountriesandflagsgame.data.pojos.ApiJsonResult
import com.alexsapps.learncountriesandflagsgame.domain.repository.CountriesApiRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchDataFromApiUseCaseImpl @Inject constructor(private val repository: CountriesApiRepository) : FetchDataFromApiUseCase {

    override suspend fun invoke(): Flow<ApiJsonResult> {
        return repository.fetchAllCountriesData()
    }
}