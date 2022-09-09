package com.eritlab.cryptotracker.fragment.topGainers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.eritlab.cryptotracker.R
import com.eritlab.cryptotracker.api.CryptoDataService
import com.eritlab.cryptotracker.api.CryptoRetrofitHelper
import com.eritlab.cryptotracker.databinding.FragmentTopGainersBinding
import com.eritlab.cryptotracker.model.CryptoCurrency
import com.eritlab.cryptotracker.repository.ApiDataRepository
import com.eritlab.cryptotracker.repository.Response
import retrofit2.Retrofit

class TopGainersFragment : Fragment() {
    private lateinit var binding: FragmentTopGainersBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopGainersBinding.inflate(layoutInflater)
        val cryptoDataService =
            CryptoRetrofitHelper.getInstance().create(CryptoDataService::class.java)
        val repository = ApiDataRepository(cryptoDataService)
        val viewModel = ViewModelProvider(
            this,
            TopGainerFragmentViewModelFactory(repository)
        )[TopGainerFragmentViewModel::class.java]

        viewModel.result.observe(requireActivity()) {
            when (it) {
                is Response.Loading -> {}
                is Response.Success -> {


                }
                is Response.Error -> {}

            }
        }


        return binding.root
    }

}