package com.engin.cointrack.core.network.util

import retrofit2.Response

inline fun <reified T : Any> Response<T>.getBodyOrThrowError(): T {
    if (isSuccessful) {
        return body() ?: throw IllegalStateException("Response body is null.")
    } else {
        throw IllegalStateException("Error Occurred")
    }
}
