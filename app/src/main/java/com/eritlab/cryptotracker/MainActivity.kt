package com.eritlab.cryptotracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eritlab.cryptotracker.databinding.ActivityMainBinding
import com.eritlab.cryptotracker.fragment.dashboard.DashboardFragment
import com.eritlab.cryptotracker.fragment.portfolio.PortfolioFragment
import com.eritlab.cryptotracker.fragment.watchlist.WatchListFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    //saving fragment
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("attachedFragment", true)
        super.onSaveInstanceState(outState)
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
                .replace(R.id.parentFragmentViewer, DashboardFragment()).commit()
        }
        binding.bottomBar.setOnItemSelectedListener { item ->
            when (item) {
                0 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.parentFragmentViewer, DashboardFragment()).commit()
                }
                1 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.parentFragmentViewer, PortfolioFragment()).commit()
                }
                2 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.parentFragmentViewer, WatchListFragment()).commit()
                }
            }

        }

    }
}