package com.eritlab.cryptotracker.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AuditInfo(
    val auditStatus: Int,
    val auditTime: String,
    val auditor: String,
    val coinId: String,
    val contractAddress: String,
    val contractPlatform: String,
    val reportUrl: String,
    val score: String
):Parcelable