package com.engin.cointrack.core.common.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

fun <T> Flow<T>.asResource(): Flow<Resource<T>> {
    return this
        .map<T, Resource<T>> {
            Resource.Success(it)
        }
        .onStart { emit(Resource.Loading) }
        .catch { emit(Resource.Error(it)) }
}
