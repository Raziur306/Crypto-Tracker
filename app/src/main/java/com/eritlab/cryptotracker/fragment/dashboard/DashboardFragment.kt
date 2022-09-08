package com.eritlab.cryptotracker.fragment.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.eritlab.cryptotracker.R
import com.eritlab.cryptotracker.adapter.SliderAdapter
import com.eritlab.cryptotracker.adapter.TopCurrencyAdapter
import com.eritlab.cryptotracker.api.CryptoDataService
import com.eritlab.cryptotracker.api.CryptoRetrofitHelper
import com.eritlab.cryptotracker.databinding.FragmentDashboardBinding
import com.eritlab.cryptotracker.model.MarketModel
import com.eritlab.cryptotracker.repository.ApiDataRepository
import com.eritlab.cryptotracker.repository.Response

class DashboardFragment : Fragment() {
    private lateinit var binding: FragmentDashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(layoutInflater)

        val cryptoDataService =
            CryptoRetrofitHelper.getInstance().create(CryptoDataService::class.java)
        val repository = ApiDataRepository(cryptoDataService)
        val viewModel =
            ViewModelProvider(
                requireActivity(),
                DashboardFragmentViewModelFactory(repository)
            )[DashboardFragmentViewModel::class.java]


        viewModel.result.observe(requireActivity()) {
            when (it) {
                is Response.Loading -> {}
                is Response.Success -> {
                    setTopCurrencyAdapter(it.data!!)
                }
                is Response.Error -> {
                    Toast.makeText(requireContext(), it.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }


        setCurrencyImageSlider()
        return binding.root
    }

    private fun setTopCurrencyAdapter(data: MarketModel) {
        binding.topCurrencyRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.topCurrencyRecycler.adapter = TopCurrencyAdapter(data.data.cryptoCurrencyList)
    }

    //currency image slider
    private fun setCurrencyImageSlider() {
        val listOfSliderImage = ArrayList<Int>()
        listOfSliderImage.add(R.drawable.btc_banner)
        listOfSliderImage.add(R.drawable.ethereum_banner)
        listOfSliderImage.add(R.drawable.binance_banner)
        listOfSliderImage.add(R.drawable.crypto_banner)
        binding.imageSlider.apply {
            setSliderAdapter(SliderAdapter(listOfSliderImage))
            startAutoCycle()
        }
    }

}