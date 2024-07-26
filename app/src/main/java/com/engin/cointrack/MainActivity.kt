package com.engin.cointrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.engin.cointrack.designsystem.theme.CoinTrackTheme
import com.engin.cointrack.ui.CoinTrackApp
import com.engin.cointrack.ui.rememberCoinTrackAppState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(lightScrim, darkScrim),
        )
        setContent {
            val appState = rememberCoinTrackAppState(
//                networkMonitor = networkMonitor,
                isLogin = viewModel.isLogin(),
            )
            CoinTrackTheme {
                CoinTrackApp(appState = appState)
            }
        }
    }
}

/**
 * The default light scrim, as defined by androidx and the platform:
 * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:activity/activity/src/main/java/androidx/activity/EdgeToEdge.kt;l=35-38;drc=27e7d52e8604a080133e8b842db10c89b4482598
 */
@Suppress("MagicNumber")
val lightScrim = android.graphics.Color.argb(0xe6, 0xFF, 0xFF, 0xFF)

/**
 * The default dark scrim, as defined by androidx and the platform:
 * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:activity/activity/src/main/java/androidx/activity/EdgeToEdge.kt;l=40-44;drc=27e7d52e8604a080133e8b842db10c89b4482598
 */
@Suppress("MagicNumber")
val darkScrim = android.graphics.Color.argb(0x80, 0x1b, 0x1b, 0x1b)
