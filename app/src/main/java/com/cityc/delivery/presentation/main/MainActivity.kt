package com.cityc.delivery.presentation.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.cityc.delivery.R
import com.cityc.delivery.databinding.ActivityMainBinding
import com.cityc.delivery.di.Scopes
import com.cityc.delivery.extensions.laziness
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import toothpick.Toothpick

class MainActivity : AppCompatActivity() {

    private val scope by laziness {
        Toothpick.openScope(Scopes.APP)
    }

    private val navigator by laziness {
        SupportAppNavigator(this, R.id.flContent)
    }

    private val navigationHolder: NavigatorHolder by laziness {
        scope.getInstance(NavigatorHolder::class.java)
    }

    private val viewModel by laziness {
        val factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return scope.getInstance(modelClass)
            }
        }
        ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        initBinding()
    }

    private fun initBinding() {
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.vm = viewModel
    }

    override fun onResumeFragments() {
        super.onResumeFragments()

        navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()

        navigationHolder.removeNavigator()
    }

    companion object {
        fun getStartIntent(context: Context, extras: Bundle?): Intent {
            val intent = Intent(context, MainActivity::class.java)
            if (extras != null) {
                intent.putExtras(extras)
            }

            return intent
        }
    }
}
