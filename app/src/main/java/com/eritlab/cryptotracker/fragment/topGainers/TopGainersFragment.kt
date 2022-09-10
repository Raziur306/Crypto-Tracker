package com.eritlab.cryptotracker.fragment.topGainers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.eritlab.cryptotracker.adapter.TopGainersAndLosersAdapter
import com.eritlab.cryptotracker.databinding.FragmentTopGainersBinding
import com.eritlab.cryptotracker.model.CryptoCurrency
import java.util.ArrayList

class TopGainersFragment : Fragment() {
    private lateinit var binding: FragmentTopGainersBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopGainersBinding.inflate(layoutInflater)
        val topGainerList = arguments?.getParcelableArrayList<CryptoCurrency>("data")
        setAdapter(topGainerList)
        return binding.root
    }

    private fun setAdapter(data: ArrayList<CryptoCurrency>?) {
        binding.topGainerRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = TopGainersAndLosersAdapter(data!!)

        }

    }
}