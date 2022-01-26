package com.github.ephelsa.okmoviesplace.model

data class HourMinDuration(
    val hours: Int,
    val minutes: Int
) {
    override fun toString(): String {
        return "${hours}h${minutes}m"
    }
}
