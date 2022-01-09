package com.github.ephelsa.okmoviesplace.local

import android.content.Context
import com.github.ephelsa.okmoviesplace.db.OKMoviesPlaceDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class SQLDelightDriverFactory(
    private val context: Context
) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(OKMoviesPlaceDatabase.Schema, context, "okmovies.db")
    }
}