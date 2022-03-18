package com.racofix.architecture.topics.platform

sealed class Result<out T> {
    object Loading : Result<Nothing>()
    data class Success<T>(val data: T) : Result<T>()
    data class Failure(val exception: Throwable) : Result<Nothing>()
}