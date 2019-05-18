package com.cityc.delivery.entity

data class Restaurant(
    val id: String = "",
    val name: String = "",
    val rating: Float = 0f,
    val foodType: String = ""
) {
    companion object {
        const val collection = "restaurants"
    }
}