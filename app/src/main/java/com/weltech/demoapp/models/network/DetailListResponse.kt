package com.weltech.demoapp.models.network

import com.weltech.demoapp.models.Genre
import com.weltech.demoapp.models.NetworkResponseModel

data class DetailListResponse(
  val id: Int,
  val imdb_id: String,
  val genres: List<Genre>
) : NetworkResponseModel
