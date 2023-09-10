package io.github.sotfheif.camsdoors

import io.github.sotfheif.camsdoors.data.CamsDoorsRepository
import io.github.sotfheif.camsdoors.data.NetworkCamsDoorsRepository
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO


import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json


interface AppContainer {
    val camsDoorsRepository: CamsDoorsRepository
}

class DefaultAppContainer: AppContainer{
    private val httpClient = HttpClient(CIO) {
        expectSuccess = true
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                }
            )
        }
    }

    override val camsDoorsRepository: CamsDoorsRepository by lazy {
        NetworkCamsDoorsRepository(httpClient)
    }
}