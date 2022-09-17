package com.eritlab.cryptotracker.fragment.watchlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.eritlab.cryptotracker.model.MarketModel
import com.eritlab.cryptotracker.repository.ApiDataRepository
import com.eritlab.cryptotracker.repository.Response
import kotlinx.coroutines.launch

class WatchListFragmentViewModel(private val repository: ApiDataRepository) : ViewModel() {
    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            repository.getResult()
        }
    }

    val responseData: LiveData<Response<MarketModel>> get() = repository.liveData
}