package com.racofix.architecture.topics.repository

import com.racofix.architecture.topics.platform.Result
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun movieDetails(id: Int): Flow<Result<String>>
}