package com.eritlab.cryptotracker.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eritlab.cryptotracker.R
import com.eritlab.cryptotracker.databinding.TopLossGainerRecyclerBinding
import com.eritlab.cryptotracker.model.CryptoCurrency
import com.eritlab.cryptotracker.model.Data
import com.eritlab.cryptotracker.model.MarketModel

class TopGainersAdapter(private val gainerList: List<CryptoCurrency>) :
    RecyclerView.Adapter<TopGainersAdapter.ViewHolder>() {
    class ViewHolder(val binding: TopLossGainerRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            TopLossGainerRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.binding.root.context)
            .load("https://s2.coinmarketcap.com/static/img/coins/64x64/${gainerList[position].id}.png")
            .thumbnail(
                Glide.with(holder.binding.root.context).load(listOf(R.drawable.loading))
            )
            .into(holder.binding.currencyImage)

        Glide.with(holder.binding.root.context)
            .load("https://s3.coinmarketcap.com/generated/sparklines/web/7d/usd/${gainerList[position].id}.svg")
            .thumbnail(
                Glide.with(holder.binding.root.context).load(listOf(R.drawable.loading))
            )
            .into(holder.binding.sparkImage)

        holder.binding.marketCap.text = gainerList[position].quotes[0].marketCap.toString()
        holder.binding.priceUsd.text =
            "+${String.format("0.2f", gainerList[position].quotes[0].price)}"
        holder.binding.currencyName.text = gainerList[position].name
        holder.binding.symbol.text = gainerList[position].symbol
    }

    override fun getItemCount(): Int {
        return gainerList.size
    }
}