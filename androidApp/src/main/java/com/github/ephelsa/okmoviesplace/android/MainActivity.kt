package com.github.ephelsa.okmoviesplace.android

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.github.ephelsa.okmoviesplace.model.Genre
import com.github.ephelsa.okmoviesplace.repository.GenreRepository
import kotlinx.coroutines.launch
import org.kodein.di.DIAware
import org.kodein.di.android.closestDI
import org.kodein.di.instance

class MainActivity : AppCompatActivity(), DIAware {

    override val di by closestDI()

    private val genreRepository: GenreRepository by instance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        getData {
            tv.text = it.toString()
        }
    }

    private fun getData(onResult: (Genre?) -> Unit) {
        lifecycleScope.launch {
            onResult(genreRepository.movieById(9648))
        }
    }
}
