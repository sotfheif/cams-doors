package io.github.sotfheif.camsdoors.data

import kotlinx.serialization.Serializable

@Serializable
data class CamFromResp(
    val name: String,
    val snapshot: String,
    val room: String?,
    val id: Int,
    val favorites: Boolean,
    val rec: Boolean
)