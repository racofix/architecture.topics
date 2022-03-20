package com.racofix.architecture.domain.entity

import com.racofix.architecture.domain.Result

typealias MovieUiState = Result<Movie>.() -> Unit

data class Movie(val id: String)
