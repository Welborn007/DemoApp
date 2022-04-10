package com.weltech.demoapp.repository

import androidx.annotation.WorkerThread
import com.weltech.sandwich.suspendOnSuccess
import com.weltech.demoapp.api.service.MovieDetailService
import com.weltech.demoapp.models.SingleLiveEvent
import com.weltech.demoapp.room.MovieDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber

class MovieRepository constructor(
    private val movieDetailService: MovieDetailService,
    private val movieDao: MovieDao
) : Repository {

  init {
    Timber.d("Injection MovieRepository")
  }

    val mutableDetailData: SingleLiveEvent<String> = SingleLiveEvent()

  @WorkerThread
  fun loadDetailList(id: Int) = flow {
    val movie = movieDao.getMovie(id)
    var genre = movie.genres
    if (genre.isNullOrEmpty()) {
        val response = movieDetailService.fetchDetails(id)
        response.suspendOnSuccess {
        genre = data.genres
        movie.genres = genre
        movie.imdb_id = data.imdb_id
        mutableDetailData.postValue(movie.imdb_id)
        emit(genre)
        movieDao.updateMovie(movie)
      }
    } else {
      emit(genre)
    }
  }.flowOn(Dispatchers.IO)

}
