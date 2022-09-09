package com.eritlab.cryptotracker.fragment.topGainers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eritlab.cryptotracker.repository.ApiDataRepository

class TopGainerFragmentViewModelFactory(private val repository: ApiDataRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TopGainerFragmentViewModel(repository) as T
    }

}