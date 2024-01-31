package com.alexsapps.learncountriesandflagsgame.data.sources.api

import com.alexsapps.learncountriesandflagsgame.data.pojos.ApiJsonResult
import retrofit2.Response
import retrofit2.http.GET

interface RestCountriesApi {

    @GET("/v3.1/all?fields=name,capital,flags,region,independent")
    suspend fun getAllCountriesDetails(): Response<ApiJsonResult>
}