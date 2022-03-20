package com.racofix.architecture.topics.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelProviders {

//    @Provides
//    @ViewModelScoped
//    fun providerMovieRepository(): MovieRepository =
//        MovieRepositoryImpl()
}