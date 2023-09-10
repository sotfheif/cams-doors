package io.github.sotfheif.camsdoors.data

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

private const val TAG = "CamsDoorsRepository"
private const val CAMS_URL = "http://cars.cprogroup.ru/api/rubetek/cameras/"
private const val DOORS_URL = "http://cars.cprogroup.ru/api/rubetek/doors/"
private const val TEST_URL = "https://catfact.ninja/fact"

interface CamsDoorsRepository {
    suspend fun getCamsDoors(): Pair<List<Cam>, List<Door>>
    //suspend fun getCams(): List<Cam>
    //suspend fun getDoors(): List<Door>
    //suspend fun getCamsResponse(): CamsResponse
    //suspend fun getDoorsResponse(): DoorsResponse
}

class NetworkCamsDoorsRepository(private val client: HttpClient) : CamsDoorsRepository {
    override suspend fun getCamsDoors(): Pair<List<Cam>, List<Door>> {
        lateinit var cams: List<Cam>
        lateinit var doors: List<Door>
        client.use {

            val doorsResponse: DoorsResponse? = it.get(DOORS_URL).body()
            Log.d(TAG, "doorsOk")
            doors = doorsResponse?.data ?: emptyList()

            val camsResponse: CamsResponse? = it.get(CAMS_URL).body()
            Log.d(TAG, "camsOk")
            cams = camsResponse?.data?.cameras ?: emptyList()


        }
        Log.d(TAG, "cams = " + cams.map{it.toString()}.reduce{cam1, cam2 -> "$cam1,  $cam2" })
        return Pair(cams, doors)
    }
}