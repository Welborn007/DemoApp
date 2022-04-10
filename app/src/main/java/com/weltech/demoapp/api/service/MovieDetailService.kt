package com.weltech.demoapp.api.service

import com.weltech.sandwich.ApiResponse
import com.weltech.demoapp.models.network.DetailListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDetailService {

  @GET("/3/movie/{movie_id}?&language=en-US")
  suspend fun fetchDetails(@Path("movie_id") id: Int): ApiResponse<DetailListResponse>
}
