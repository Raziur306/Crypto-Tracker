package com.eritlab.cryptotracker.fragment.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eritlab.cryptotracker.repository.ApiDataRepository


@Suppress("UNCHECKED_CAST")
class DashboardFragmentViewModelFactory(private val repository: ApiDataRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DashboardFragmentViewModel(repository) as T
    }
}