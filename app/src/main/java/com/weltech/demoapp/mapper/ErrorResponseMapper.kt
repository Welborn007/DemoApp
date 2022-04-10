package com.weltech.demoapp.mapper

import com.weltech.sandwich.ApiErrorModelMapper
import com.weltech.sandwich.ApiResponse
import com.weltech.sandwich.message
import com.weltech.demoapp.models.network.TheMovieErrorResponse

object ErrorResponseMapper : ApiErrorModelMapper<TheMovieErrorResponse> {

  override fun map(apiErrorResponse: ApiResponse.Failure.Error<*>): TheMovieErrorResponse {
    return TheMovieErrorResponse(apiErrorResponse.statusCode.code, apiErrorResponse.message())
  }
}
