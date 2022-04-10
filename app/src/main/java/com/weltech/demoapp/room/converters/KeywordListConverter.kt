package com.weltech.demoapp.room.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.weltech.demoapp.models.Genre

open class KeywordListConverter {
  @TypeConverter
  fun fromString(value: String): List<Genre>? {
    val listType = object : TypeToken<List<Genre>>() {}.type
    return Gson().fromJson<List<Genre>>(value, listType)
  }

  @TypeConverter
  fun fromList(list: List<Genre>?): String {
    val gson = Gson()
    return gson.toJson(list)
  }
}
