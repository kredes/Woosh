package com.cityc.delivery.presentation.restaurant

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel
import com.cityc.delivery.entity.Restaurant
import com.google.firebase.firestore.FirebaseFirestore
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class RestaurantListViewModel @Inject constructor(
    private val router: Router,
    private val db: FirebaseFirestore
) : ViewModel() {

    val restaurantListItems = ObservableArrayList<RestaurantViewModel>()

    init {
        loadRestaurants()
    }

    private fun loadRestaurants() {
        db.collection(Restaurant.collection).get()
            .addOnSuccessListener { result ->
                val restaurants = result.toObjects(Restaurant::class.java)
                val vms = createItemViewModels(restaurants)
                restaurantListItems.addAll(vms)
            }
            .addOnFailureListener {
                val e = it
            }
    }

    private fun createItemViewModels(restaurants: List<Restaurant>): List<RestaurantViewModel> {
        return restaurants.map { restaurant -> RestaurantViewModel().also { it.restaurant.set(restaurant) } }
    }
}