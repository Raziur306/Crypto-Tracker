package com.eritlab.cryptotracker.fragment.topLosers

import android.os.Build
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
import com.eritlab.cryptotracker.databinding.FragmentTopLosersBinding
import com.eritlab.cryptotracker.fragment.fragmentDetails.DetailsFragment
import com.eritlab.cryptotracker.model.CryptoCurrency
import java.util.ArrayList

class TopLosersFragment : Fragment(), RecyclerViewInterface {
    private lateinit var binding: FragmentTopLosersBinding
    private lateinit var topLoserList: ArrayList<CryptoCurrency>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopLosersBinding.inflate(layoutInflater)
        topLoserList = if (Build.VERSION_CODES.TIRAMISU <= Build.VERSION.SDK_INT) {
            arguments?.getParcelableArrayList("data", CryptoCurrency::class.java)!!
        } else {
            @Suppress("DEPRECATION")
            arguments?.getParcelableArrayList<CryptoCurrency>("data")!!
        }
        setAdapter(topLoserList)
        return binding.root
    }

    private fun setAdapter(data: ArrayList<CryptoCurrency>?) {
        binding.topLoserRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = TopGainersAndLosersAdapter(data!!, this@TopLosersFragment)
        }
    }

    override fun onItemClick(position: Int) {
        val bundle = Bundle()
        bundle.putParcelable("data", topLoserList[position] as Parcelable)
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.parentFragmentViewer, DetailsFragment().apply {
                arguments = bundle
            }, "TOP_LOSERS_FRAGMENT").addToBackStack(null).commit()
    }
}

