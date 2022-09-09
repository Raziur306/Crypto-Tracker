package com.eritlab.cryptotracker.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.eritlab.cryptotracker.fragment.topGainers.TopGainersFragment
import com.eritlab.cryptotracker.fragment.topLosers.TopLosersFragment


class TabLayoutViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                TopGainersFragment()
            }
            1 -> {
                TopLosersFragment()
            }
            else -> {
                TopGainersFragment()
            }
        }
    }
}