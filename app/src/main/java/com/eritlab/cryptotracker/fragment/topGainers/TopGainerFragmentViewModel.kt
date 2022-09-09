package com.eritlab.cryptotracker.fragment.topGainers

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eritlab.cryptotracker.model.MarketModel
import com.eritlab.cryptotracker.repository.ApiDataRepository
import com.eritlab.cryptotracker.repository.Response
import kotlinx.coroutines.launch

class TopGainerFragmentViewModel(private val repository: ApiDataRepository) : ViewModel() {
    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            repository.getResult()
        }
    }

    val result: LiveData<Response<MarketModel>> get() = repository.liveData

}