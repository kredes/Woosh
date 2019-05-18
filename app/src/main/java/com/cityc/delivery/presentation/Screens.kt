package com.cityc.delivery.presentation

import androidx.fragment.app.Fragment
import com.cityc.delivery.presentation.restaurant.RestaurantListFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {
    class RestaurantList() : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return RestaurantListFragment()
        }
    }
}