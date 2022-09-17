package com.eritlab.cryptotracker.adapter

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.eritlab.cryptotracker.fragment.topGainers.TopGainersFragment
import com.eritlab.cryptotracker.fragment.topLosers.TopLosersFragment
import com.eritlab.cryptotracker.model.CryptoCurrency


class TabLayoutViewPagerAdapter(
    fragment: Fragment,
    private val topGainerList: ArrayList<CryptoCurrency>,
    private val topLooserList: ArrayList<CryptoCurrency>
) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", topGainerList)
                TopGainersFragment().apply {
                    arguments = bundle
                }
            }
            1 -> {
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", topLooserList)
                TopLosersFragment().apply {
                    arguments = bundle
                }
            }
            else -> {
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", topGainerList)
                TopGainersFragment().apply {
                    arguments = bundle
                }
            }
        }
    }
}