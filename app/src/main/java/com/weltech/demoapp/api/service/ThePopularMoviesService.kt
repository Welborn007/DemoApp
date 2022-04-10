package com.weltech.demoapp.api.service

import com.weltech.sandwich.ApiResponse
import com.weltech.demoapp.models.network.DiscoverMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ThePopularMoviesService {

  @GET("/3/movie/popular?&language=en-US")
  suspend fun fetchDiscoverMovie(@Query("page") page: Int): ApiResponse<DiscoverMovieResponse>
}
