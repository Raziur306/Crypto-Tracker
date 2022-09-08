package com.eritlab.cryptotracker.repository

sealed class Response<T>(val data: T? = null, val errorMessage: String? = null) {
    class Loading<T> : Response<T>()
    class Success<T>(data: T?) : Response<T>(data = data)
    class Error<T>(errorMessage: String?) : Response<T>(errorMessage = errorMessage)
}