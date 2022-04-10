package com.weltech.demoapp.models.network

import com.weltech.demoapp.models.NetworkResponseModel
import com.weltech.demoapp.models.entity.Movie

data class DiscoverMovieResponse(
  val page: Int,
  val results: List<Movie>,
  val total_results: Int,
  val total_pages: Int
) : NetworkResponseModel
