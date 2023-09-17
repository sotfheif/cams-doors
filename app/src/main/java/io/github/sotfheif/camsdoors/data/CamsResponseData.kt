package io.github.sotfheif.camsdoors.data

import kotlinx.serialization.Serializable

@Serializable
data class CamsResponseData(
    val room: List<String>,
    val cameras: List<CamFromResp>
    )