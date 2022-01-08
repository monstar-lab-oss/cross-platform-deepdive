package com.github.ephelsa.okmoviesplace.localdatasource

import com.github.ephelsa.okmoviesplace.db.OKMoviesPlaceDatabase
import com.squareup.sqldelight.db.SqlDriver
import kotlin.jvm.Volatile
import kotlin.native.concurrent.ThreadLocal

expect class SQLDelightDriverFactory {
    fun createDriver(): SqlDriver
}

class OkMoviesPlaceDB private constructor() {

    @ThreadLocal
    companion object {
        @Volatile
        private var INSTANCE: OKMoviesPlaceDatabase? = null

        fun instance(driverFactory: SQLDelightDriverFactory): OKMoviesPlaceDatabase {
            return INSTANCE ?: run {
                val driver = driverFactory.createDriver()
                val db = OKMoviesPlaceDatabase(driver)
                INSTANCE = db
                db
            }
        }
    }
}
