package io.github.sotfheif.camsdoors.data

fun transformRemoteDataToUniformData(
    remoteData: Pair<List<CamFromResp>, List<DoorFromResp>>
): List<CardDb> {
    return remoteData.first.map { transformCamFromRespToCardDb(it) } +
            remoteData.second.map { transformDoorFromRespToCardDb(it) }
}

fun transformCamFromRespToCardDb(camFromResp: CamFromResp): CardDb {
    return camFromResp.run {
        CardDb(
            name = name,
            snapshot = snapshot,
            room = room,
            id = id,
            favorites = favorites,
            isCam = true,
            rec = rec,
        )
    }
}

fun transformDoorFromRespToCardDb(doorFromResp: DoorFromResp): CardDb {
    return doorFromResp.run {
        CardDb(
            name = name,
            snapshot = snapshot,
            room = room,
            id = id,
            favorites = favorites,
            isCam = false,
        )
    }
}