package com.racofix.architecture.topics.data.repository

import com.racofix.architecture.topics.platform.Result
import com.racofix.architecture.topics.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MoviesRepositoryImpl:MoviesRepository {
    override fun movieDetails(id: Int):Flow<Result<String>> = flow<Result<String>> {  }.flowOn(Dispatchers.IO)
}