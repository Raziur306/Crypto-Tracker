package com.eritlab.cryptotracker.fragment.market

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eritlab.cryptotracker.repository.ApiDataRepository

@Suppress("UNCHECKED_CAST")
class FragmentMarketViewModelFactory(private val repository: ApiDataRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FragmentMarketViewModel(repository) as T
    }
}