package com.example.learncountriesandflagsgame.data.usecases

import com.example.learncountriesandflagsgame.data.pojos.ApiJsonResult
import kotlinx.coroutines.flow.Flow

interface FetchDataFromApiUseCase {

    suspend operator fun invoke(): Flow<ApiJsonResult>
}