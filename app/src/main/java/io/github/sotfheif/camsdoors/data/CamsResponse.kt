package io.github.sotfheif.camsdoors.data

import kotlinx.serialization.Serializable

@Serializable
data class CamsResponse(
    val success: Boolean,
    val data: CamsResponseData
)