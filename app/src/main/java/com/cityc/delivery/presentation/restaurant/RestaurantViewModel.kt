package com.cityc.delivery.presentation.restaurant

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.cityc.delivery.entity.Restaurant

class RestaurantViewModel : ViewModel() {

    val restaurant = ObservableField<Restaurant>()
}