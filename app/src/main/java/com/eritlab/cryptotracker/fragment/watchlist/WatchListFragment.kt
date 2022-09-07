package com.eritlab.cryptotracker.fragment.watchlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eritlab.cryptotracker.R
import com.eritlab.cryptotracker.databinding.FragmentPortfolioBinding
import com.eritlab.cryptotracker.databinding.FragmentWatchListBinding

class WatchListFragment : Fragment() {
    private lateinit var binding: FragmentWatchListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWatchListBinding.inflate(layoutInflater)



        return binding.root
    }

}