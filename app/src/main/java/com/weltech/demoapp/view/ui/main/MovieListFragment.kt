package com.weltech.demoapp.view.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.weltech.bindables.BindingFragment
import com.weltech.demoapp.R
import com.weltech.demoapp.databinding.MainFragmentMovieBinding
import com.weltech.demoapp.view.adapter.MovieListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment :
  BindingFragment<MainFragmentMovieBinding>(R.layout.main_fragment_movie) {

  private val vm: MainActivityViewModel by viewModels()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    super.onCreateView(inflater, container, savedInstanceState)
    return binding {
      adapter = MovieListAdapter()
      viewModel = vm
    }.root
  }
}
