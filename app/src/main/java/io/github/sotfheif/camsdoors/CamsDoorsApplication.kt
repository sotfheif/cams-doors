package io.github.sotfheif.camsdoors

import android.app.Application
import android.util.Log
import coil.ImageLoader
import coil.ImageLoaderFactory

class CamsDoorsApplication : Application(), ImageLoaderFactory {
    lateinit var container: AppContainer
    lateinit var imageLoader: ImageLoader
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
        imageLoader = ImageLoader.Builder(this)
            //.crossfade(true)
            .build()
    }

    override fun newImageLoader(): ImageLoader {
        Log.d("CamsDoorsApplication", "newImageLoader()")
        return imageLoader
    }
}