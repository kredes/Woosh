package com.cityc.delivery.di.modules

import com.google.firebase.firestore.FirebaseFirestore
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import toothpick.config.Module

class AppModule : Module() {
    init {
        val cicerone = Cicerone.create()
        bind(Router::class.java).toInstance(cicerone.router)
        bind(NavigatorHolder::class.java).toInstance(cicerone.navigatorHolder)

        val db = FirebaseFirestore.getInstance()
        bind(FirebaseFirestore::class.java).toInstance(db)
    }
}