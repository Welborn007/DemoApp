
package com.weltech.demoapp.view.ui.main

import android.os.Bundle
import com.weltech.bindables.BindingActivity
import com.weltech.demoapp.R
import com.weltech.demoapp.databinding.ActivityMainBinding
import com.weltech.demoapp.extension.applyOnPageSelected
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    initializeUI()
  }

  private fun initializeUI() {
    var fragmentTransaction = supportFragmentManager.beginTransaction()
    fragmentTransaction.replace(R.id.main_view,MovieListFragment()).commit()
  }
}
