package io.github.sotfheif.camsdoors.data

import kotlinx.serialization.Serializable

@Serializable
data class DoorsResponse(
    val success: Boolean,
    val data: List<DoorFromResp>
    )