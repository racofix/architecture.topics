package com.racofix.architecture.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

abstract class UseCase<in Params, Type> {

    abstract fun flowOf(params: Params): Flow<Result<Type>>

    operator fun invoke(
        params: Params
    ): Flow<Result<Type>> =
        flowOf(params)
            .onStart { emit(Result.Loading) }
            .catch { emit(Result.Error(it)) }
            .onCompletion { emit(Result.Idle) }
            .flowOn(Dispatchers.IO)
}