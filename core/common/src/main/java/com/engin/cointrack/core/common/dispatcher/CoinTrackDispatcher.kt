package com.engin.cointrack.core.common.base.dispatcher

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val dispatcher: CoinTrackDispatcher)

enum class CoinTrackDispatcher {
    Default,
    IO,
}
