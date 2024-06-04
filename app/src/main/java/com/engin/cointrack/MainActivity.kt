package com.engin.cointrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.engin.cointrack.designsystem.theme.CoinTrackTheme
import com.engin.cointrack.ui.CoinTrackApp
import com.engin.cointrack.ui.rememberCoinTrackAppState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val appState = rememberCoinTrackAppState(
//                networkMonitor = networkMonitor,
            )
            CoinTrackTheme {
                CoinTrackApp(appState = appState)
            }
        }
    }
}
