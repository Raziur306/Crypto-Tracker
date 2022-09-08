package com.eritlab.cryptotracker.fragment.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eritlab.cryptotracker.model.MarketModel
import com.eritlab.cryptotracker.repository.ApiDataRepository
import com.eritlab.cryptotracker.repository.Response
import kotlinx.coroutines.launch

class DashboardFragmentViewModel(private val repository: ApiDataRepository) : ViewModel() {
    init {
        getResult()
    }

    private fun getResult() {
        viewModelScope.launch {
            repository.getResult()
        }
    }

    val result: LiveData<Response<MarketModel>> get() = repository.liveData

}