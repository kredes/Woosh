package com.cityc.delivery.presentation.base

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.cityc.delivery.di.Scopes
import com.cityc.delivery.extensions.laziness
import toothpick.Scope
import toothpick.Toothpick
import toothpick.config.Module
import toothpick.locators.NoFactoryFoundException

abstract class BaseFragment : Fragment() {

    protected val scope: Scope by laziness {
        Toothpick.openScopes(Scopes.APP, this)
    }

    override fun onDestroy() {
        super.onDestroy()

        Toothpick.closeScope(this)
    }

    protected fun setSupportActionBar(toolbar: Toolbar) {
        (activity as? AppCompatActivity)?.setSupportActionBar(toolbar)
    }

    protected inline fun <reified T : ViewModel> createViewModel(
        noinline moduleInit: ((Module) -> Unit)? = null,
        fragment: Fragment = this
    ): T {
        val factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val scope = Toothpick.openScopes(Scopes.APP, this@BaseFragment)
                if (moduleInit != null) {
                    scope.installModules(object : Module() {
                        init {
                            moduleInit.invoke(this)
                        }
                    })
                }

                return try {
                    scope.getInstance(modelClass)
                } catch (e: NoFactoryFoundException) {
                    modelClass.newInstance()
                }
            }
        }

        return ViewModelProviders.of(fragment, factory).get(T::class.java)
    }
}