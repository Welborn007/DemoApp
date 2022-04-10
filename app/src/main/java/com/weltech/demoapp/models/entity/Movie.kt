package com.weltech.demoapp.models.entity

import android.os.Parcelable
import androidx.room.Entity
import com.weltech.demoapp.models.Genre
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(primaryKeys = [("id")])
data class Movie(
  var page: Int,
  var genres: List<Genre>? = ArrayList(),
  val poster_path: String?,
  val adult: Boolean,
  val overview: String,
  val release_date: String?,
  val genre_ids: List<Int>,
  val id: Int,
  val original_title: String,
  val original_language: String,
  val title: String,
  var imdb_id: String?,
  val backdrop_path: String?,
  val popularity: Float,
  val vote_count: Int,
  val video: Boolean,
  val vote_average: Float
) : Parcelable
