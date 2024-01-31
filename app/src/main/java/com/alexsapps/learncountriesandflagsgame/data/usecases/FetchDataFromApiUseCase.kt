package com.alexsapps.learncountriesandflagsgame.data.usecases

import com.alexsapps.learncountriesandflagsgame.data.pojos.ApiJsonResult
import kotlinx.coroutines.flow.Flow

interface FetchDataFromApiUseCase {

    suspend operator fun invoke(): Flow<ApiJsonResult>
}