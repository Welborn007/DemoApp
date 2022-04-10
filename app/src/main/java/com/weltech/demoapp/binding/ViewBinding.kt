package com.weltech.demoapp.binding

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import com.weltech.demoapp.api.Api
import com.weltech.demoapp.extension.requestGlideListener
import com.weltech.demoapp.extension.visible
import com.weltech.demoapp.models.entity.Movie
import com.weltech.whatif.whatIfNotNull
import com.weltech.whatif.whatIfNotNullOrEmpty

object ViewBinding {

  @JvmStatic
  @BindingAdapter("loadPaletteImage", "loadPaletteTarget")
  fun bindLoadImage(view: AppCompatImageView, url: String, targetView: View) {
    Glide.with(view)
      .load(url)
      .listener(
        GlidePalette.with(url)
          .use(BitmapPalette.Profile.VIBRANT)
          .intoBackground(targetView)
          .crossfade(true)
      )
      .into(view)
  }

  @JvmStatic
  @BindingAdapter("visibilityByResource")
  fun bindVisibilityByResource(view: View, anyList: List<Any>?) {
    anyList.whatIfNotNullOrEmpty {
      view.visible()
    }
  }

  @JvmStatic
  @SuppressLint("SetTextI18n")
  @BindingAdapter("bindReleaseDate")
  fun bindReleaseDate(view: TextView, movie: Movie) {
    view.text = "Release Date : ${movie.release_date}"
  }

  @JvmStatic
  @BindingAdapter("bindBackDrop")
  fun bindBackDrop(view: ImageView, movie: Movie) {
    movie.backdrop_path.whatIfNotNull(
      whatIf = {
        Glide.with(view.context).load(Api.getBackdropPath(it))
          .listener(view.requestGlideListener())
          .into(view)
      },
      whatIfNot = {
        Glide.with(view.context).load(Api.getBackdropPath(movie.poster_path))
          .listener(view.requestGlideListener())
          .into(view)
      }
    )
  }
}
