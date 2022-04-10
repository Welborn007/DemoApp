package com.weltech.demoapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.weltech.demoapp.models.entity.Movie
import com.weltech.demoapp.room.converters.IntegerListConverter
import com.weltech.demoapp.room.converters.KeywordListConverter
import com.weltech.demoapp.room.converters.StringListConverter

@Database(
  entities = [(Movie::class)],
  version = 1, exportSchema = false
)
@TypeConverters(
  value = [
    (StringListConverter::class), (IntegerListConverter::class),
    (KeywordListConverter::class)
  ]
)
abstract class AppDatabase : RoomDatabase() {
  abstract fun movieDao(): MovieDao
}
