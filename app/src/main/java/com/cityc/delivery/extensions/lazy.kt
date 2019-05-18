package com.cityc.delivery.extensions

fun <T> laziness(initializer: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.NONE, initializer)