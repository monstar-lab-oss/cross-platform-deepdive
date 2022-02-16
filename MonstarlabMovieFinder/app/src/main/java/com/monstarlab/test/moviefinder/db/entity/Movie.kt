package com.monstarlab.test.moviefinder.db.entity

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import androidx.room.ColumnInfo
import androidx.room.Entity
import java.io.Serializable

@Entity
class Movie : Serializable {
    @JvmField
    @PrimaryKey
    @SerializedName("id")
    var id = 0

    @JvmField
    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    var posterPath: String? = null

    @JvmField
    @SerializedName("adult")
    var isAdult = false

    @JvmField
    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    var overview: String? = null

    @JvmField
    @SerializedName("release_date")
    var releaseDate: String? = null

    @JvmField
    @ColumnInfo(name = "title")
    @SerializedName("title")
    var title: String? = null

    @JvmField
    @ColumnInfo(name = "favorite")
    @SerializedName("favorite")
    var favorite = false
}