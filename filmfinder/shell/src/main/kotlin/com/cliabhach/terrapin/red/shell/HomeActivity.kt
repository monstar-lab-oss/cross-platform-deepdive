package com.cliabhach.terrapin.red.shell

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/**
 *
 */
class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.background, HomeFragment.newInstance())
                .replace(R.id.foreground, SearchFragment.newInstance())
                .commitNow()
        }
    }
}