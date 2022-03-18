package com.racofix.architecture.topics.platform

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

abstract class UseCase<Type, in Params> {

    abstract suspend fun execute(params: Params): Flow<Result<Type>>

    suspend operator fun invoke(
        params: Params
    ): Flow<Result<Type>> =
        execute(params)
            .onStart { emit(Result.Loading) }
            .catch { emit(Result.Failure(it)) }
            .flowOn(Dispatchers.IO)
}