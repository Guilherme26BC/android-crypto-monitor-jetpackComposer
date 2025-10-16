package guilherme26bc.com.github.android_crypto_monitor_jetpackcomposer

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import guilherme26bc.com.github.android_crypto_monitor_jetpackcomposer.screens.BitcoinScreen
import guilherme26bc.com.github.android_crypto_monitor_jetpackcomposer.ui.theme.Android_crypto_monitor_jetpackComposerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Android_crypto_monitor_jetpackComposerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        BitcoinScreen(modifier = Modifier.padding(innerPadding))

                }
            }
        }
    }
}

