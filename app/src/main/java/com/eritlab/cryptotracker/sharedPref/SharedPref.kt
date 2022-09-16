package com.eritlab.cryptotracker.sharedPref

import android.app.Application
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.collections.ArrayList

class SharedPref : Application() {
    private lateinit var sharedPref: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private val gson = Gson()
    override fun onCreate() {
        super.onCreate()
        sharedPref =
            getSharedPreferences("crypto_share", MODE_PRIVATE)
        editor = sharedPref.edit()
    }

    fun setWatchList(watchList: ArrayList<String>) {
        val json = gson.toJson(watchList)
        editor.putString("watchList", json)
        editor.apply()
    }

    fun getWatchList(): ArrayList<String> {
        val json = sharedPref.getString("watchList", ArrayList<String>().toString())
        val type = object : TypeToken<ArrayList<String>>() {}.type
        return gson.fromJson(json, type) as ArrayList<String>
    }
}