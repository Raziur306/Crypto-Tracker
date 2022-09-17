package com.eritlab.cryptotracker.fragment.market

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eritlab.cryptotracker.model.MarketModel
import com.eritlab.cryptotracker.repository.ApiDataRepository
import com.eritlab.cryptotracker.repository.Response
import kotlinx.coroutines.launch

class FragmentMarketViewModel(private val repository: ApiDataRepository) : ViewModel() {
    init {
        getResult()
    }

    fun getResult() {
        viewModelScope.launch {
            repository.getResult()
        }
    }

    val result: LiveData<Response<MarketModel>> get() = repository.liveData
}