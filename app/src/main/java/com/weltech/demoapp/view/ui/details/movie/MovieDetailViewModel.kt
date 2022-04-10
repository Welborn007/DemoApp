package com.weltech.demoapp.view.ui.details.movie

import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import androidx.annotation.MainThread
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import com.weltech.bindables.BindingViewModel
import com.weltech.bindables.asBindingProperty
import com.weltech.demoapp.models.Genre
import com.weltech.demoapp.models.entity.Movie
import com.weltech.demoapp.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
  private val movieRepository: MovieRepository
) : BindingViewModel() {

  private val movieIdSharedFlow: MutableSharedFlow<Int> = MutableSharedFlow(replay = 1)
  private val detailListFlow = movieIdSharedFlow.flatMapLatest {
    movieRepository.loadDetailList(it)
  }

  @get:Bindable
  val genreList: List<Genre>? by detailListFlow.asBindingProperty(viewModelScope, null)

  init {
    Timber.d("Injection MovieDetailViewModel")
  }

  @MainThread
  fun getMovieListFromId(id: Int) = movieIdSharedFlow.tryEmit(id)

  fun observeMovieDetail() = movieRepository.mutableDetailData
}
