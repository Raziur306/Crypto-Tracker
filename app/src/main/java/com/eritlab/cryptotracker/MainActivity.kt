package com.eritlab.cryptotracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentManager
import com.eritlab.cryptotracker.databinding.ActivityMainBinding
import com.eritlab.cryptotracker.fragment.dashboard.DashboardFragment
import com.eritlab.cryptotracker.fragment.portfolio.PortfolioFragment
import com.eritlab.cryptotracker.fragment.watchlist.WatchListFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    //saving fragment
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("attachedFragment", true)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null || savedInstanceState.getBoolean(
                "attachedFragment",
                false
            )
        ) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentViewer, DashboardFragment()).commit()
        }
        binding.bottomBar.setOnItemSelectedListener { item ->
            when (item) {
                0 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentViewer, DashboardFragment()).commit()
                }
                1 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentViewer, PortfolioFragment()).commit()
                }
                2 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentViewer, WatchListFragment()).commit()
                }
            }

        }

    }
}