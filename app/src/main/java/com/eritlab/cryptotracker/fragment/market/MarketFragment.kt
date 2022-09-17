package com.eritlab.cryptotracker.fragment.market

import android.os.Bundle
import android.os.Parcelable
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
import com.eritlab.cryptotracker.databinding.FragmentMarketBinding
import com.eritlab.cryptotracker.fragment.fragmentDetails.DetailsFragment
import com.eritlab.cryptotracker.model.CryptoCurrency
import com.eritlab.cryptotracker.model.MarketModel
import com.eritlab.cryptotracker.repository.ApiDataRepository
import com.eritlab.cryptotracker.repository.Response


class MarketFragment : Fragment(), RecyclerViewInterface {
    private lateinit var binding: FragmentMarketBinding
    private  val data: ArrayList<CryptoCurrency> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMarketBinding.inflate(layoutInflater)
        val api = CryptoRetrofitHelper.getInstance().create(CryptoDataService::class.java)
        val repository = ApiDataRepository(api)
        val viewMode = ViewModelProvider(
            this,
            FragmentMarketViewModelFactory(repository)
        )[FragmentMarketViewModel::class.java]


        viewMode.result.observe(requireActivity()) {
            when (it) {
                is Response.Success -> {
                    data.addAll(it.data!!.data.cryptoCurrencyList)
                    setData()
                }
                is Response.Error -> {}
                is Response.Loading -> {}
            }
        }


        return binding.root
    }

    private fun setData() {
        binding.marketRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.marketRecycler.adapter = TopGainersAndLosersAdapter(data,this)
    }

    override fun onItemClick(position: Int) {
        val bundle = Bundle()
        bundle.putParcelable("data", data[position] as Parcelable)
        requireActivity().supportFragmentManager.beginTransaction().replace(
            R.id.parentFragmentViewer,
            DetailsFragment().apply {
                arguments = bundle
            },"MARKET_DETAILS_FRAGMENT"
        ).addToBackStack(null).commit()
    }


}