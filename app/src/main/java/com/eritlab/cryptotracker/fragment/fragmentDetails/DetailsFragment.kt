package com.eritlab.cryptotracker.fragment.fragmentDetails

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.eritlab.cryptotracker.R
import com.eritlab.cryptotracker.databinding.FragmentDetailsBinding
import com.eritlab.cryptotracker.model.CryptoCurrency

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        val data = arguments?.getParcelable<CryptoCurrency>("data")
        if (data != null) {
            setData(data)
            setChart(data)
        }





        return binding.root
    }

    private fun setChart(data: CryptoCurrency) {
        binding.webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        binding.webView.loadUrl(
            "https://s.tradingview.com/widgetembed/?frameElementId=tradingview_76d87&symbol" + data.symbol
                .toString() + "USD&interval=1H&hidesidetoolbar=1&hidetoptoolbar=1&symboledit=1&saveimage=1&toolbarbg=F1F3F6&studies=[]&hideideas=1&theme=Dark&style=1&timezone=Etc%2FUTC&studies_overrides={}&overrides={}&enabled_features=[]&disabled_features=[]&locale=en&utm_source=coinmarketcap.com&utm_medium=widget&utm_campaign=chart&utm_term=BTCUSDT"
        )
    }

    private fun setData(data: CryptoCurrency) {
        Glide.with(requireActivity())
            .load("https://s2.coinmarketcap.com/static/img/coins/64x64/${data.id}.png")
            .thumbnail(Glide.with(requireActivity()).load(R.drawable.loading))
            .into(binding.currencyImage)
        binding.currentPrice.text = "$" + data.quotes[0].price.toString()

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
}