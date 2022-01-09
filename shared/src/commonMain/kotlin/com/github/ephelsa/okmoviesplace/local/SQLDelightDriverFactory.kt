package com.github.ephelsa.okmoviesplace.local

import com.squareup.sqldelight.db.SqlDriver

expect class SQLDelightDriverFactory {
    fun createDriver(): SqlDriver
}
