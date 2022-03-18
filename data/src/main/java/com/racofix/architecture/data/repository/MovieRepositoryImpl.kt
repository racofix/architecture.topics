package com.racofix.architecture.data.repository

import com.racofix.architecture.domain.Result
import com.racofix.architecture.domain.entity.Movie
import com.racofix.architecture.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl : MovieRepository {
    override fun movieDetails(id: Int): Flow<Result<Movie>> = flow {
        emit(Result.Success(Movie("1357")))
    }
}