package com.eritlab.cryptotracker.fragment.watchlist

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.eritlab.cryptotracker.R
import com.eritlab.cryptotracker.adapter.RecyclerViewInterface
import com.eritlab.cryptotracker.adapter.TopGainersAndLosersAdapter
import com.eritlab.cryptotracker.api.CryptoDataService
import com.eritlab.cryptotracker.api.CryptoRetrofitHelper
import com.eritlab.cryptotracker.databinding.FragmentWatchListBinding
import com.eritlab.cryptotracker.fragment.fragmentDetails.DetailsFragment
import com.eritlab.cryptotracker.model.CryptoCurrency
import com.eritlab.cryptotracker.model.MarketModel
import com.eritlab.cryptotracker.repository.ApiDataRepository
import com.eritlab.cryptotracker.repository.Response
import com.eritlab.cryptotracker.sharedPref.SharedPref

class WatchListFragment : Fragment(), RecyclerViewInterface {
    private lateinit var binding: FragmentWatchListBinding
    private var responseData: MarketModel? = null
    private val filteredList = ArrayList<CryptoCurrency>()
    private lateinit var sharedPref: SharedPref
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWatchListBinding.inflate(layoutInflater)
        sharedPref = requireContext().applicationContext as SharedPref
        val api = CryptoRetrofitHelper.getInstance().create(CryptoDataService::class.java)
        val repository = ApiDataRepository(api)
        val viewModel = ViewModelProvider(
            this,
            WatchListFragmentViewModelFactory(repository)
        )[WatchListFragmentViewModel::class.java]

        //observe data
        viewModel.responseData.observe(requireActivity()) {
            when (it) {
                is Response.Error -> {}
                is Response.Loading -> {}
                is Response.Success -> {
                    responseData = it.data!!
                    filterAndSet(responseData!!)
                }
            }
        }


        try {
            requireActivity().supportFragmentManager.addOnBackStackChangedListener {
                if (responseData != null)
                    filterAndSet(responseData!!)
            }
        } catch (e: Exception) {
            Log.d("WatchList Listener", e.message.toString())
        }



        return binding.root
    }

    private fun filterAndSet(data: MarketModel) {
        filteredList.clear()
        val matchList = sharedPref.getWatchList()
        data.data.cryptoCurrencyList.forEach {
            if (matchList.contains(it.id.toString()))
                filteredList.add(it)
        }
        //set adapter
        binding.watchListRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.watchListRecycler.adapter = TopGainersAndLosersAdapter(filteredList, this)
    }

    override fun onItemClick(position: Int) {
        val bundle = Bundle()
        bundle.putParcelable("data", filteredList[position] as Parcelable)
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.parentFragmentViewer, DetailsFragment().apply {
                arguments = bundle
            }, "WATCH_LIST_FRAGMENT").addToBackStack(null).commit()
    }

}