package com.eritlab.cryptotracker.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.eritlab.cryptotracker.R
import com.eritlab.cryptotracker.databinding.TopCurrecnyLayoutBinding
import com.eritlab.cryptotracker.model.CryptoCurrency
import com.eritlab.cryptotracker.model.Data
import com.eritlab.cryptotracker.model.MarketModel

class TopCurrencyAdapter(private val topCurrencyList: List<CryptoCurrency>) :
    RecyclerView.Adapter<TopCurrencyAdapter.ViewHolder>() {
    class ViewHolder(val binding: TopCurrecnyLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            TopCurrecnyLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("CheckResult", "SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.currencyName.text = topCurrencyList[position].name

        if (topCurrencyList[position].quotes?.get(0)!!.percentChange24h > 0)
            holder.binding.currencyValue.apply {
                text = "+" + topCurrencyList[position].quotes?.get(0)?.percentChange24h
                setTextColor(Color.GREEN)
            } else {
            holder.binding.currencyValue.apply {
                text = topCurrencyList[position].quotes?.get(0)?.percentChange24h.toString()
                setTextColor(Color.RED)
            }
        }
        holder.binding.currencyValue.text =
            topCurrencyList[position].quotes?.get(0)?.price.toString()

        Log.d("Ronju", topCurrencyList[position].id.toString())

        Glide.with(holder.binding.root.context)
            .load("https://s2.coinmarketcap.com/static/img/coins/64x64/${topCurrencyList[position].id}.png")
            .thumbnail(Glide.with(holder.binding.root.context).load(R.drawable.loading))
            .into(
                holder.binding.currencyImage
            )

    }

    override fun getItemCount(): Int {
        return topCurrencyList.size
    }
}