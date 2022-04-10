package com.weltech.demoapp.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.weltech.baserecyclerviewadapter.RecyclerViewPaginator
import com.weltech.demoapp.R
import com.weltech.demoapp.extension.visible
import com.weltech.demoapp.models.Genre
import com.weltech.demoapp.models.entity.Movie
import com.weltech.demoapp.view.adapter.MovieListAdapter
import com.weltech.demoapp.view.ui.main.MainActivityViewModel
import com.weltech.whatif.whatIfNotNull
import com.weltech.whatif.whatIfNotNullOrEmpty

object RecyclerViewBinding {

  @JvmStatic
  @BindingAdapter("adapterMovieList")
  fun bindAdapterMovieList(view: RecyclerView, movies: List<Movie>?) {
    movies.whatIfNotNull {
      (view.adapter as? MovieListAdapter)?.addMovieList(it)
    }
  }

  @JvmStatic
  @BindingAdapter("paginationMovieList")
  fun paginationMovieList(view: RecyclerView, viewModel: MainActivityViewModel) {
    RecyclerViewPaginator(
      recyclerView = view,
      isLoading = { viewModel.isMovieListLoading },
      loadMore = { viewModel.postMoviePage(it) },
      onLast = { false }
    ).run {
      threshold = 4
      currentPage = 1
    }
  }

  @JvmStatic
  @BindingAdapter("mapKeywordList")
  fun bindMapKeywordList(chipGroup: ChipGroup, genres: List<Genre>?) {
    genres.whatIfNotNullOrEmpty {
      chipGroup.visible()
      for (keyword in it) {
        chipGroup.addView(
          Chip(chipGroup.context).apply {
            text = keyword.name
            isCheckable = false
            setTextAppearanceResource(R.style.ChipTextStyle)
            setChipBackgroundColorResource(R.color.colorPrimary)
          }
        )
      }
    }
  }
}
