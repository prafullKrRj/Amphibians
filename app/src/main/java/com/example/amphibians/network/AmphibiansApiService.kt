package com.example.amphibians.network

import com.example.amphibians.model.AmphibianDetail
import retrofit2.http.GET

interface AmphibiansApiService {

    @GET("amphibians")
    suspend fun getDetails(): List<AmphibianDetail>
}