package com.eritlab.cryptotracker.fragment.dashboard

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.eritlab.cryptotracker.R
import com.eritlab.cryptotracker.adapter.SliderAdapter
import com.eritlab.cryptotracker.adapter.TabLayoutViewPagerAdapter
import com.eritlab.cryptotracker.adapter.TopCurrencyAdapter
import com.eritlab.cryptotracker.api.CryptoDataService
import com.eritlab.cryptotracker.api.CryptoRetrofitHelper
import com.eritlab.cryptotracker.databinding.FragmentDashboardBinding
import com.eritlab.cryptotracker.model.CryptoCurrency
import com.eritlab.cryptotracker.model.MarketModel
import com.eritlab.cryptotracker.repository.ApiDataRepository
import com.eritlab.cryptotracker.repository.Response
import com.google.android.material.tabs.TabLayoutMediator

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


        viewModel.result.observe(requireActivity()) { obData ->
            when (obData) {
                is Response.Loading -> {}
                is Response.Success -> {
                    obData.data.let {
                        if (it != null) {
                            setTopCurrencyAdapter(it)
                            setTabLayout(it)
                        }

                    }

                }
                is Response.Error -> {
                    Toast.makeText(requireContext(), obData.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }




        setCurrencyImageSlider()
        return binding.root
    }

    private fun setTabLayout(data: MarketModel) {
        val topGainerList = ArrayList<CryptoCurrency>()
        val topLooserList = ArrayList<CryptoCurrency>()
        data.data.cryptoCurrencyList.forEach { cryptoCurrency ->
            if (cryptoCurrency.quotes?.get(0)!!.percentChange24h > 0)
                topGainerList.add(cryptoCurrency)
            else
                topLooserList.add(cryptoCurrency)
        }
        try {
            binding.tabContentViewPager.adapter =
                TabLayoutViewPagerAdapter(this, topGainerList, topLooserList)
            TabLayoutMediator(binding.tabLayout, binding.tabContentViewPager) { tab, position ->
                tab.text = when (position) {
                    0 -> {
                        "Top Gainers"
                    }
                    1 -> {
                        "Top Losers"
                    }
                    else -> {
                        "Top Gainers"
                    }
                }
            }.attach()
        } catch (e: Exception) {
            Log.d("DashboardFragmentTab", e.message.toString())
        }

    }


    private fun setTopCurrencyAdapter(data: MarketModel) {
        try {
            binding.topCurrencyRecycler.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            binding.topCurrencyRecycler.adapter = TopCurrencyAdapter(data.data.cryptoCurrencyList)
        } catch (e: Exception) {
            Log.d("DashBoardFragment", e.message.toString())
        }

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