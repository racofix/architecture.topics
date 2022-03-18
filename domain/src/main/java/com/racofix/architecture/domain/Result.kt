package com.racofix.architecture.domain

sealed class Result<out T> {
    object Idle : Result<Nothing>()
    object Loading : Result<Nothing>()
    data class Success<T>(val data: T?) : Result<T>()
    data class Error(val exception: Throwable) : Result<Nothing>()
}