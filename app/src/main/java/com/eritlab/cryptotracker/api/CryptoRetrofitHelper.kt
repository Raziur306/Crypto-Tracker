package com.eritlab.cryptotracker.api

import com.eritlab.cryptotracker.utils.URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CryptoRetrofitHelper {
    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(URL.CRYPTO_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}