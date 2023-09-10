package io.github.sotfheif.camsdoors

import android.app.Application

class CamsDoorsApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}