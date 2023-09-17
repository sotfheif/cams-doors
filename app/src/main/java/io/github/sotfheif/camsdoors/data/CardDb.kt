package io.github.sotfheif.camsdoors.data


data class CardDb(
    val name: String,
    val snapshot: String,
    val room: String?,
    val id: Int,
    val favorites: Boolean,
    val isCam: Boolean,
    val rec: Boolean = false,
)

private const val TEST_IMAGE_URL =
    "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0b/Cat_poster_1.jpg/1280px-Cat_poster_1.jpg"

fun getCardDbStub(id: Int) = CardDb(
    name = "name1",
    snapshot = TEST_IMAGE_URL,
    room = "Гостиная",
    id = id,
    favorites = true,
    isCam = false,
    rec = false,
)
