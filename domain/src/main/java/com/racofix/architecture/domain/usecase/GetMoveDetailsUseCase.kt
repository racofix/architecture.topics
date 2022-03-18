package com.racofix.architecture.domain.usecase

import com.racofix.architecture.domain.Result
import com.racofix.architecture.domain.UseCase
import com.racofix.architecture.domain.entity.Movie
import com.racofix.architecture.domain.repository.MovieRepository
import com.racofix.architecture.domain.usecase.GetMoveDetailsUseCase.Params
import kotlinx.coroutines.flow.Flow

class GetMoveDetailsUseCase(private val repository: MovieRepository) : UseCase<Params, Movie>() {

    data class Params(val id: Int)

    override suspend fun execute(params: Params): Flow<Result<Movie>> =
        repository.movieDetails(params.id)
}