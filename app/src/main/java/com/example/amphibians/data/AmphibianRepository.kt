package com.example.amphibians.data

import com.example.amphibians.model.AmphibianDetail
import com.example.amphibians.network.AmphibiansApiService

interface AmphibianRepository {
    suspend fun getDetails(): List<AmphibianDetail>
}

class NetworkAmphibianDetailRepository (
    private val apiService: AmphibiansApiService
): AmphibianRepository {
    override suspend fun getDetails(): List<AmphibianDetail> = apiService.getDetails()
}