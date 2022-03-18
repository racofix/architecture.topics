package com.racofix.architecture.domain.repository

import com.racofix.architecture.domain.Result
import com.racofix.architecture.domain.entity.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun movieDetails(id: Int): Flow<Result<Movie>>
}