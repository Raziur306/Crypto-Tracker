package com.eritlab.cryptotracker.fragment.watchlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eritlab.cryptotracker.repository.ApiDataRepository


@Suppress("UNCHECKED_CAST")
class WatchListFragmentViewModelFactory(private val repository: ApiDataRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WatchListFragmentViewModel(repository) as T
    }
}