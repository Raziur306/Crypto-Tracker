package com.eritlab.cryptotracker.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eritlab.cryptotracker.R
import com.eritlab.cryptotracker.databinding.TopLossGainerRecyclerBinding
import com.eritlab.cryptotracker.model.CryptoCurrency

class TopGainersAndLosersAdapter(private val gainerList: List<CryptoCurrency>) :
    RecyclerView.Adapter<TopGainersAndLosersAdapter.ViewHolder>() {
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
            .load("https://s3.coinmarketcap.com/generated/sparklines/web/7d/usd/${gainerList[position].id}.png")
            .thumbnail(
                Glide.with(holder.binding.root.context).load(listOf(R.drawable.loading))
            )
            .into(holder.binding.sparkImage)

        holder.binding.report.apply {
            text =
                if (gainerList[position].quotes[0].percentChange24h > 0)
                    "+${String.format("%.2f", gainerList[position].quotes[0].percentChange24h)}%"
                else
                    "${String.format("%.2f", gainerList[position].quotes[0].percentChange24h)}%"
            setTextColor(Color.GREEN)
        }
        holder.binding.priceUsd.text =
            String.format("%.2f", gainerList[position].quotes[0].price)
        holder.binding.currencyName.text = gainerList[position].name
        holder.binding.symbol.text = gainerList[position].symbol
        holder.binding.serial.text = gainerList[position].id.toString()
    }

    override fun getItemCount(): Int {
        return gainerList.size
    }
}