package com.eritlab.cryptotracker.fragment.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.eritlab.cryptotracker.R
import com.eritlab.cryptotracker.adapter.SliderAdapter
import com.eritlab.cryptotracker.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {
    private lateinit var binding: FragmentDashboardBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(layoutInflater)

        val listOfSliderImage = ArrayList<Int>()
        listOfSliderImage.add(R.drawable.btc_banner)
        listOfSliderImage.add(R.drawable.ethereum_banner)
        listOfSliderImage.add(R.drawable.binance_banner)
        listOfSliderImage.add(R.drawable.crypto_banner)
        binding.imageSlider.apply {
            setSliderAdapter(SliderAdapter(listOfSliderImage))
//            isAutoCycle = true
            startAutoCycle()
//            autoCycleDirection = 3
        }

        return binding.root
    }

}