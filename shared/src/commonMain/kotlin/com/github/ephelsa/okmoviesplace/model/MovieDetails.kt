package com.github.ephelsa.okmoviesplace.model

data class MovieDetails(
    val id: Int,
    val imagePath: ImagePath,
    val title: String,
    val isAdult: Boolean,
    val votesAverage: Double,
    val genres: List<Genre>,
    val description: String,
    val language: String,
)
