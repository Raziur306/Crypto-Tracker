package com.eritlab.cryptotracker.fragment.topLosers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eritlab.cryptotracker.R
import com.eritlab.cryptotracker.databinding.FragmentTopLosersBinding

class TopLosersFragment : Fragment() {
    private lateinit var binding: FragmentTopLosersBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopLosersBinding.inflate(layoutInflater)


        return binding.root
    }

}

