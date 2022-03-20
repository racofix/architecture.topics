package com.racofix.architecture.data.repository

import com.racofix.architecture.domain.Result
import com.racofix.architecture.domain.entity.Movie
import com.racofix.architecture.domain.repository.MovieRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor() : MovieRepository {
    override fun movieDetails(id: Int): Flow<com.racofix.architecture.domain.Result<Movie>> = flow {
        delay(3000)
        emit(com.racofix.architecture.domain.Result.Success(Movie("1357")))
    }
}