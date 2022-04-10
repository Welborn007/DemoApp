package com.weltech.demoapp

import android.app.Application
import com.facebook.stetho.Stetho
import com.weltech.sandwich.SandwichInitializer
import com.weltech.demoapp.operator.GlobalResponseOperator
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class TheMoviesApplication : Application() {

  override fun onCreate() {
    super.onCreate()

    // initialize global sandwich operator
    SandwichInitializer.sandwichOperator = GlobalResponseOperator<Any>(this)

    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }

    Stetho.initializeWithDefaults(this)
  }
}
