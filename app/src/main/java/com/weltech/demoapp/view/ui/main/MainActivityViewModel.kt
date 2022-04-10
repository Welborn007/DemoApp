package com.weltech.demoapp.view.ui.main

import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import com.weltech.bindables.BindingViewModel
import com.weltech.bindables.asBindingProperty
import com.weltech.bindables.bindingProperty
import com.weltech.demoapp.models.entity.Movie
import com.weltech.demoapp.repository.DiscoverRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
  private val discoverRepository: DiscoverRepository
) : BindingViewModel() {

  @get:Bindable
  var isMovieListLoading: Boolean by bindingProperty(false)
    private set

  private val moviePageStateFlow: MutableStateFlow<Int> = MutableStateFlow(1)
  private val movieListFlow = moviePageStateFlow.flatMapLatest {
    isMovieListLoading = true
    discoverRepository.loadMovies(it) {
      isMovieListLoading = false
    }
  }

  @get:Bindable
  val movieList: List<Movie> by movieListFlow.asBindingProperty(viewModelScope, emptyList())

  init {
    Timber.d("injection MainActivityViewModel")
  }

  fun postMoviePage(page: Int) = moviePageStateFlow.tryEmit(page)
}
