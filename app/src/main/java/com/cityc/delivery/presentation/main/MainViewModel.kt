package com.cityc.delivery.presentation.main

import androidx.lifecycle.ViewModel
import com.cityc.delivery.presentation.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val router: Router
) : ViewModel() {

    init {
        navigateToDemandList()
    }

    private fun navigateToDemandList() {
        router.replaceScreen(Screens.RestaurantList())
    }
}