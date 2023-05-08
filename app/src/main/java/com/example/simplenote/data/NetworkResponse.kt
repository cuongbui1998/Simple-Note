package com.example.simplenote.data

sealed class NetworkResponse<out T : Any> {
    /**
     * Success response with data
     */
    data class Success<T : Any>(val data: T) : NetworkResponse<T>()

    /**
     * Failure response
     */
    data class Error(val exception: Throwable) : NetworkResponse<Nothing>()
}