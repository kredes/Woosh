package com.cityc.delivery.presentation.restaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import com.cityc.delivery.BR
import com.cityc.delivery.R
import com.cityc.delivery.extensions.laziness
import com.cityc.delivery.presentation.base.BaseFragment
import me.tatarka.bindingcollectionadapter2.ItemBinding

class RestaurantListFragment : BaseFragment() {

    private val viewModel: RestaurantListViewModel by laziness {
        createViewModel<RestaurantListViewModel>()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<com.cityc.delivery.databinding.FragmentRestaurantListBinding>(
            inflater, R.layout.fragment_restaurant_list, container, false
        )
        binding.vm = viewModel
        binding.restaurantItemBinding = ItemBinding.of<ViewModel> { itemBinding, _, item ->
            itemBinding.set(BR.vm, R.layout.view_item_restaurant)
        }

        return binding.root
    }

    companion object {
        const val ARG_RESTAURANT_ID = "restaurant_id"

        @JvmStatic
        fun newInstance(restaurantId: String) =
            RestaurantListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_RESTAURANT_ID, restaurantId)
                }
            }
    }
}
