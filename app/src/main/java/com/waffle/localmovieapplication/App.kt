package com.waffle.localmovieapplication

import android.app.Application
import com.waffle.core.module.networkModule
import com.waffle.core.module.repositoryModule
import com.waffle.core.module.roomModule
import com.waffle.core.module.useCaseModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@App)
            modules(listOf(networkModule, repositoryModule, roomModule, useCaseModule))
        }
    }
}