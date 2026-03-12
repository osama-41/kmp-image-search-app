package dev.himanshu.imagesearchapp

import android.app.Application
import dev.himanshu.imagesearchapp.di.initKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}