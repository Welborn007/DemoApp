package com.weltech.demoapp.view.ui.details.movie

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.weltech.bindables.BindingActivity
import com.weltech.bundler.bundleNonNull
import com.weltech.bundler.intentOf
import com.weltech.demoapp.R
import com.weltech.demoapp.databinding.ActivityMovieDetailBinding
import com.weltech.demoapp.models.entity.Movie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity :
  BindingActivity<ActivityMovieDetailBinding>(R.layout.activity_movie_detail) {

  private val vm: MovieDetailViewModel by viewModels()
  private val intentMovie: Movie by bundleNonNull(MOVIE_ID)
  private lateinit var viewModel: MovieDetailViewModel
  private var imdbID: String? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding {
      activity = this@MovieDetailActivity
      viewModel = vm.apply { getMovieListFromId(intentMovie.id) }
      movie = intentMovie
    }

    viewModel = ViewModelProvider(this).get(MovieDetailViewModel::class.java)

    viewModel.observeMovieDetail().observe(this, Observer<String> {
      imdbID = it
    })

    binding.imdb.setOnClickListener {
      if(!imdbID.isNullOrEmpty())
      {
        val builder = CustomTabsIntent.Builder()
        builder.setShowTitle(true)
        builder.setShareState(CustomTabsIntent.SHARE_STATE_ON)
        builder.setInstantAppsEnabled(true)
        val customBuilder = builder.build()
        customBuilder.launchUrl(this, Uri.parse("https://www.imdb.com/title/" + imdbID))
      }
    }
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (item.itemId == android.R.id.home) onBackPressed()
    return false
  }

  companion object {
    private const val MOVIE_ID = "movie"
    fun startActivityModel(context: Context?, movie: Movie?) {
      context?.intentOf<MovieDetailActivity> {
        putExtra(MOVIE_ID to movie)
        startActivity(context)
      }
    }
  }
}
