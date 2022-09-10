package com.eritlab.cryptotracker.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Platform(
    val id: Int,
    val name: String,
    val slug: String,
    val symbol: String,
    val token_address: String
) : Parcelable