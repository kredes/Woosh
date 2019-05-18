package com.cityc.delivery

import android.app.Application
import com.cityc.delivery.di.Scopes
import com.cityc.delivery.di.modules.AppModule
import toothpick.Toothpick
import toothpick.configuration.Configuration
import toothpick.smoothie.module.SmoothieApplicationModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        initIoc()
    }

    private fun initIoc() {
        if (BuildConfig.DEBUG) {
            Toothpick.setConfiguration(Configuration.forDevelopment().preventMultipleRootScopes())
        } else {
            Toothpick.setConfiguration(Configuration.forProduction())
        }

        val appScope = Toothpick.openScope(Scopes.APP)
        appScope.installModules(AppModule(), SmoothieApplicationModule(this))
    }
}