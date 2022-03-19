package com.racofix.architecture.domain.usecase

import com.racofix.architecture.domain.Result
import com.racofix.architecture.domain.UseCase
import com.racofix.architecture.domain.entity.Movie
import com.racofix.architecture.domain.repository.MovieRepository
import com.racofix.architecture.domain.usecase.GetMoveDetailsUseCase.Params
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoveDetailsUseCase@Inject constructor(private val repository: MovieRepository) : UseCase<Params, Movie>() {

    data class Params(val id: Int)

    override fun flowOf(params: Params): Flow<Result<Movie>> =
        repository.movieDetails(params.id)
}