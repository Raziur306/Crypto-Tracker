package com.eritlab.cryptotracker.fragment.topGainers

import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.eritlab.cryptotracker.R
import com.eritlab.cryptotracker.adapter.RecyclerViewInterface
import com.eritlab.cryptotracker.adapter.TopGainersAndLosersAdapter
import com.eritlab.cryptotracker.databinding.FragmentTopGainersBinding
import com.eritlab.cryptotracker.fragment.fragmentDetails.DetailsFragment
import com.eritlab.cryptotracker.model.CryptoCurrency
import java.util.concurrent.CyclicBarrier


class TopGainersFragment : Fragment(), RecyclerViewInterface {
    private lateinit var binding: FragmentTopGainersBinding
    private lateinit var topGainerList: ArrayList<CryptoCurrency>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopGainersBinding.inflate(layoutInflater)
        topGainerList =
            if (Build.VERSION_CODES.TIRAMISU <= SDK_INT) {
                arguments?.getParcelableArrayList("data", CryptoCurrency::class.java)!!
            } else {
                @Suppress("DEPRECATION")
                arguments?.getParcelableArrayList<CryptoCurrency>("data")!!
            }
        setAdapter(topGainerList)
        return binding.root
    }

    private fun setAdapter(data: ArrayList<CryptoCurrency>?) {
        binding.topGainerRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = TopGainersAndLosersAdapter(data!!, this@TopGainersFragment)
        }

    }

    override fun onItemClick(position: Int) {
        val bundle = Bundle()
        bundle.putParcelable("data", topGainerList[position] as Parcelable)
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.parentFragmentViewer, DetailsFragment().apply {
                arguments = bundle
            }, "TOP_GAINERS_FRAGMENT").addToBackStack(null).commit()
    }
}