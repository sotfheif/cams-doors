package io.github.sotfheif.camsdoors.data

data class Cam(
    val name: String,
    val snapshot: String,
    val room: String,
    val id: Int,
    val favorites: Boolean,
    val rec: Boolean
)