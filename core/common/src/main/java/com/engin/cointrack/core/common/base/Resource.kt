
package com.engin.cointrack.core.common.base

sealed interface Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>
    data class Error(val exception: Throwable? = null) : Resource<Nothing>
    data object Loading : Resource<Nothing>


}




