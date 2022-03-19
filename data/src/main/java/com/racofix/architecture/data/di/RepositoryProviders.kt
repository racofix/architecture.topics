package com.racofix.architecture.data.di

import com.racofix.architecture.data.repository.MovieRepositoryImpl
import com.racofix.architecture.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryProviders {

    @Provides
    @ViewModelScoped
    fun providerMovieRepository(): MovieRepository =
        MovieRepositoryImpl()
}