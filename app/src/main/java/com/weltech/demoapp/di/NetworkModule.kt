package com.weltech.demoapp.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.weltech.sandwich.coroutines.CoroutinesResponseCallAdapterFactory
import com.weltech.demoapp.api.Api
import com.weltech.demoapp.api.RequestInterceptor
import com.weltech.demoapp.api.service.MovieDetailService
import com.weltech.demoapp.api.service.ThePopularMoviesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

  @Provides
  @Singleton
  fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
      .addInterceptor(RequestInterceptor())
      .addNetworkInterceptor(StethoInterceptor())
      .build()
  }

  @Provides
  @Singleton
  fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
      .client(okHttpClient)
      .baseUrl(Api.BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(CoroutinesResponseCallAdapterFactory.create())
      .build()
  }

  @Provides
  @Singleton
  fun provideTheDiscoverService(retrofit: Retrofit): ThePopularMoviesService {
    return retrofit.create(ThePopularMoviesService::class.java)
  }

  @Provides
  @Singleton
  fun provideMovieService(retrofit: Retrofit): MovieDetailService {
    return retrofit.create(MovieDetailService::class.java)
  }
}
