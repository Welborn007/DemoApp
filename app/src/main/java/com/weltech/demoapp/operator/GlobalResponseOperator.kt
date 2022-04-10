package com.weltech.demoapp.operator

import android.app.Application
import android.widget.Toast
import com.weltech.sandwich.ApiResponse
import com.weltech.sandwich.StatusCode
import com.weltech.sandwich.map
import com.weltech.sandwich.message
import com.weltech.sandwich.operators.ApiResponseSuspendOperator
import com.weltech.demoapp.mapper.ErrorResponseMapper
import com.weltech.demoapp.models.network.TheMovieErrorResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class GlobalResponseOperator<T> constructor(
  private val application: Application
) : ApiResponseSuspendOperator<T>() {

  // handle the case when the API request gets a success response.
  override suspend fun onSuccess(apiResponse: ApiResponse.Success<T>) = Unit

  // handles the case when the API request gets an error response.
  // e.g., internal server error.
  override suspend fun onError(apiResponse: ApiResponse.Failure.Error<T>) =
    withContext(Dispatchers.IO) {
      apiResponse.run {
        Timber.d(message())

        // handling error based on status code.
        when (statusCode) {
          StatusCode.InternalServerError -> toast("InternalServerError")
          StatusCode.BadGateway -> toast("BadGateway")
          else -> toast("$statusCode(${statusCode.code}): ${message()}")
        }

        /** maps the [ApiResponse.Failure.Error] to the [TheMovieErrorResponse] using the mapper. */
        map(ErrorResponseMapper) {
          Timber.d(message())
        }
      }
    }

  // handle the case when the API request gets a exception response.
  // e.g., network connection error.
  override suspend fun onException(apiResponse: ApiResponse.Failure.Exception<T>) =
    withContext(Dispatchers.Main) {
      apiResponse.run {
        Timber.d(message())
        toast(message())
      }
    }

  private fun toast(message: String) {
    Toast.makeText(application, message, Toast.LENGTH_SHORT).show()
  }
}
