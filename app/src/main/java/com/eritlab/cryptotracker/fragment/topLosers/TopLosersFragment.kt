package com.eritlab.cryptotracker.fragment.topLosers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.eritlab.cryptotracker.adapter.TopGainersAndLosersAdapter
import com.eritlab.cryptotracker.databinding.FragmentTopLosersBinding
import com.eritlab.cryptotracker.model.CryptoCurrency
import java.util.ArrayList

class TopLosersFragment : Fragment() {
    private lateinit var binding: FragmentTopLosersBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopLosersBinding.inflate(layoutInflater)
        val data = arguments?.getParcelableArrayList<CryptoCurrency>("data")
        setAdapter(data)
        return binding.root
    }

    private fun setAdapter(data: ArrayList<CryptoCurrency>?) {
        binding.topLoserRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = TopGainersAndLosersAdapter(data!!)

        }
    }
}

