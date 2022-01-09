package com.github.ephelsa.okmoviesplace.local

import com.github.ephelsa.okmoviesplace.db.OKMoviesPlaceDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class SQLDelightDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(OKMoviesPlaceDatabase.Schema, "okmovies.db")
    }
}