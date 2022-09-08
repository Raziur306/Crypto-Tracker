package com.eritlab.cryptotracker.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eritlab.cryptotracker.api.CryptoDataService
import com.eritlab.cryptotracker.model.MarketModel

class ApiDataRepository(private val cryptoDataService: CryptoDataService) {
    private val mutableLiveData = MutableLiveData<Response<MarketModel>>()
    val liveData: LiveData<Response<MarketModel>> = mutableLiveData
    suspend fun getResult() {
        try {
            val result = cryptoDataService.getData()
            if (result.body() != null) {
                mutableLiveData.postValue(Response.Success(result.body()))
            } else {
                mutableLiveData.postValue(Response.Error("Server Communication Failed"))
            }
        } catch (
            e: Exception
        ) {
            mutableLiveData.postValue(Response.Error("Something went wrong"))
        }

    }

}