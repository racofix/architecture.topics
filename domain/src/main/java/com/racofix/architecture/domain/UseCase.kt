package com.racofix.architecture.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

abstract class UseCase<in Params, Type> {

    abstract suspend fun execute(params: Params): Flow<Result<Type>>

    suspend operator fun invoke(
        params: Params
    ): Flow<Result<Type>> =
        execute(params)
            .onStart { emit(Result.Loading) }
            .catch { emit(Result.Error(it)) }
            .onCompletion { emit(Result.Idle) }
            .flowOn(Dispatchers.IO)
}