package com.racofix.architecture.topics.usecase

import com.racofix.architecture.topics.platform.Result
import com.racofix.architecture.topics.platform.UseCase
import com.racofix.architecture.topics.repository.MoviesRepository
import com.racofix.architecture.topics.usecase.GetMoveDetails.Params
import kotlinx.coroutines.flow.Flow

class GetMoveDetails(private val repository: MoviesRepository) : UseCase<String, Params>() {

    override suspend fun execute(params: Params): Flow<Result<String>> =
        repository.movieDetails(params.id)

    data class Params(val id: Int)
}