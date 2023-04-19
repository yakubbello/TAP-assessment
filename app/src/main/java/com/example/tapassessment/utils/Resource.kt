package com.example.tapassessment.utils

sealed class Resource<T>(
    val data: T? = null,
    val error: Throwable? = null
) {
    class Success<T>(data: T): Resource<T>(data)
    class Loading : Resource<Nothing>()
    class Error<T>(throwable: Throwable, data: T? = null): Resource<T>(data, throwable)

}
