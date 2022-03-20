package com.racofix.architecture.data.di

import com.racofix.architecture.data.repository.MovieRepositoryImpl
import com.racofix.architecture.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryProviders {

    @Provides
    fun providerMovieRepository(): MovieRepository =
        MovieRepositoryImpl()
}