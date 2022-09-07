package com.eritlab.cryptotracker.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.eritlab.cryptotracker.databinding.DashboardImageSilderLayoutBinding
import com.smarteist.autoimageslider.SliderViewAdapter

class SliderAdapter(private val imageList: List<Int>) :
    SliderViewAdapter<SliderAdapter.SliderAdapterVH>() {
    class SliderAdapterVH(val binding: DashboardImageSilderLayoutBinding) :
        SliderViewAdapter.ViewHolder(binding.root)

    override fun getCount(): Int {
        return imageList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?): SliderAdapterVH {
        val binding = DashboardImageSilderLayoutBinding.inflate(
            LayoutInflater.from(parent?.context),
            parent,
            false
        )
        return SliderAdapterVH(binding)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterVH?, position: Int) {
        viewHolder?.binding?.imageView?.setImageResource(imageList[position])
    }
}