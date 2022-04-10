package com.weltech.demoapp.di

import com.weltech.demoapp.api.service.MovieDetailService
import com.weltech.demoapp.api.service.ThePopularMoviesService
import com.weltech.demoapp.repository.DiscoverRepository
import com.weltech.demoapp.repository.MovieRepository
import com.weltech.demoapp.room.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

  @Provides
  @ViewModelScoped
  fun provideDiscoverRepository(
      popularMoviesService: ThePopularMoviesService,
      movieDao: MovieDao
  ): DiscoverRepository {
    return DiscoverRepository(popularMoviesService, movieDao)
  }

  @Provides
  @ViewModelScoped
  fun provideMovieRepository(
      movieDetailService: MovieDetailService,
      movieDao: MovieDao
  ): MovieRepository {
    return MovieRepository(movieDetailService, movieDao)
  }
}
