package com.eritlab.cryptotracker.fragment.portfolio

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eritlab.cryptotracker.R
import com.eritlab.cryptotracker.databinding.FragmentPortfolioBinding


class PortfolioFragment : Fragment() {
    private lateinit var binding: FragmentPortfolioBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPortfolioBinding.inflate(layoutInflater)


        return binding.root
    }


}