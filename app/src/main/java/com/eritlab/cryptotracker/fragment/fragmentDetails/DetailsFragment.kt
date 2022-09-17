package com.eritlab.cryptotracker.fragment.fragmentDetails

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.eritlab.cryptotracker.R
import com.eritlab.cryptotracker.databinding.FragmentDetailsBinding
import com.eritlab.cryptotracker.model.CryptoCurrency
import com.eritlab.cryptotracker.sharedPref.SharedPref

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private lateinit var sharedPref: SharedPref
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        val data =
            if (Build.VERSION_CODES.TIRAMISU <= Build.VERSION.SDK_INT) {
                arguments?.getParcelable("data", CryptoCurrency::class.java)!!
            } else {
                @Suppress("DEPRECATION")
                arguments?.getParcelable<CryptoCurrency>("data")!!
            }


        setData(data)
        setChart("D", data)
        sharedPref = requireActivity().applicationContext as SharedPref

        //set watch list image resource

        if (sharedPref.getWatchList().contains(data.id.toString()))
            binding.saveWatchList.setImageResource(R.drawable.ic_baseline_star_24)

        binding.webView.apply {
            settings.javaScriptEnabled = true
            setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        }

        binding.saveWatchList.setOnClickListener {
            updateWatchList(data)
        }


        binding.btn15Min.setOnClickListener {
            setChart("15", data)
            setButtonBg(
                binding.btn15Min,
                binding.btn1Hour,
                binding.btn4Hour,
                binding.btn1Day,
                binding.btn1Week,
                binding.btn1Month
            )
        }
        binding.btn1Hour.setOnClickListener {
            setChart("1H", data)
            setButtonBg(
                binding.btn1Hour,
                binding.btn15Min,
                binding.btn4Hour,
                binding.btn1Day,
                binding.btn1Week,
                binding.btn1Month
            )
        }

        binding.btn4Hour.setOnClickListener {
            setChart("4H", data)
            setButtonBg(
                binding.btn4Hour,
                binding.btn15Min,
                binding.btn1Hour,
                binding.btn1Day,
                binding.btn1Week,
                binding.btn1Month
            )
        }

        binding.btn1Day.setOnClickListener {
            setChart("D", data)
            setButtonBg(
                binding.btn1Day,
                binding.btn1Week,
                binding.btn1Month,
                binding.btn4Hour,
                binding.btn15Min,
                binding.btn1Hour,
            )
        }

        binding.btn1Week.setOnClickListener {
            setChart("W", data)
            setButtonBg(
                binding.btn1Week,
                binding.btn1Day,
                binding.btn1Month,
                binding.btn4Hour,
                binding.btn15Min,
                binding.btn1Hour,
            )
        }

        binding.btn1Month.setOnClickListener {
            setChart("M", data)
            setButtonBg(
                binding.btn1Month,
                binding.btn4Hour,
                binding.btn1Week,
                binding.btn1Day,
                binding.btn15Min,
                binding.btn1Hour,
            )
        }


        return binding.root
    }

    private fun updateWatchList(data: CryptoCurrency) {
        val list = sharedPref.getWatchList()
        if (list.contains(data.id.toString())) {
            binding.saveWatchList.setImageResource(R.drawable.ic_baseline_star_outline_24)
            list.removeAt(list.indexOf(data.id.toString()))
            Toast.makeText(requireContext(), "Removed from Watchlist", Toast.LENGTH_SHORT).show()
        } else {
            binding.saveWatchList.setImageResource(R.drawable.ic_baseline_star_24)
            list.add(data.id.toString())
            Toast.makeText(requireContext(), "Added to Watchlist", Toast.LENGTH_SHORT).show()
        }
        sharedPref.setWatchList(list)
    }


    private fun setData(data: CryptoCurrency) {
        Glide.with(requireActivity())
            .load("https://s2.coinmarketcap.com/static/img/coins/64x64/${data.id}.png")
            .thumbnail(Glide.with(requireActivity()).load(R.drawable.loading))
            .into(binding.currencyImage)
        binding.currentPrice.text = "$" + data.quotes?.get(0)!!.price.toString()

        binding.currencySymbol.text = data.symbol
        setCurrentState(data.quotes[0].percentChange24h)
    }

    private fun setCurrentState(value: Double) {
        binding.currentState.apply {
            if (value < 0) {
                text = String.format("%.2f", value)
                setTextColor(Color.RED)
                this.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_baseline_arrow_drop_down_24,
                    0,
                    0,
                    0
                )
            } else {
                text = String.format("%.2f", value)
                setTextColor(Color.GREEN)
                setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_baseline_arrow_drop_up_24,
                    0,
                    0,
                    0
                )
            }
        }
    }

    private fun setChart(time: String, data: CryptoCurrency) {
        binding.webView.loadUrl(
            "https://s.tradingview.com/widgetembed/?frameElementId=tradingview_76d87&symbol" + data.symbol
                .toString() + "USD&interval=$time&hidesidetoolbar=1&hidetoptoolbar=1&symboledit=1&saveimage=1&toolbarbg=F1F3F6&studies=[]&hideideas=1&theme=Dark&style=1&timezone=Etc%2FUTC&studies_overrides={}&overrides={}&enabled_features=[]&disabled_features=[]&locale=en&utm_source=coinmarketcap.com&utm_medium=widget&utm_campaign=chart&utm_term=BTCUSDT"
        )
    }

    private fun setButtonBg(
        button1: TextView,
        button2: TextView,
        button3: TextView,
        button4: TextView,
        button5: TextView,
        button6: TextView
    ) {
        button1.apply {
            background.setTint(Color.parseColor("#3F51B5"))
            setTextColor(Color.WHITE)
        }
        button2.apply {
            background.setTint(Color.TRANSPARENT)
            setTextColor(Color.parseColor("#7f8185"))
        }
        button3.apply {
            background.setTint(Color.TRANSPARENT)
            setTextColor(Color.parseColor("#7f8185"))
        }
        button4.apply {
            background.setTint(Color.TRANSPARENT)
            setTextColor(Color.parseColor("#7f8185"))
        }
        button5.apply {
            background.setTint(Color.TRANSPARENT)
            setTextColor(Color.parseColor("#7f8185"))
        }
        button6.apply {
            background.setTint(Color.TRANSPARENT)
            setTextColor(Color.parseColor("#7f8185"))
        }

    }

}