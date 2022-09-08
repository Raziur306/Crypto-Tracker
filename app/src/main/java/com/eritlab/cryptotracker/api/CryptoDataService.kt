package com.eritlab.cryptotracker.api

import com.eritlab.cryptotracker.model.MarketModel
import retrofit2.Response
import retrofit2.http.GET

interface CryptoDataService {
    @GET("/data-api/v3/cryptocurrency/listing?start=1&limit=500")
    suspend fun getData(): Response<MarketModel>
}