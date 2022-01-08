package com.github.ephelsa.okmoviesplace.localdatasource

import com.squareup.sqldelight.db.SqlDriver

expect class SQLDelightDriverFactory {
    fun createDriver(): SqlDriver
}
