package com.eritlab.cryptotracker.fragment.topGainers

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

class TopGainersFragment : Fragment(), RecyclerViewInterface {
    private lateinit var binding: FragmentTopGainersBinding
    private lateinit var topGainerList: ArrayList<CryptoCurrency>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopGainersBinding.inflate(layoutInflater)
        topGainerList = arguments?.getParcelableArrayList<CryptoCurrency>("data")!!
        setAdapter(topGainerList)
        return binding.root
    }

    private fun setAdapter(data: ArrayList<CryptoCurrency>?) {
        binding.topGainerRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = TopGainersAndLosersAdapter(data!!, this@TopGainersFragment)
            isNestedScrollingEnabled = false
        }

    }

    override fun onItemClick(position: Int) {
        val bundle = Bundle()
        bundle.putParcelable("data", topGainerList[position] as Parcelable)
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.parentFragmentViewer, DetailsFragment().apply {
                arguments = bundle
            }).addToBackStack(null).commit()
    }
}