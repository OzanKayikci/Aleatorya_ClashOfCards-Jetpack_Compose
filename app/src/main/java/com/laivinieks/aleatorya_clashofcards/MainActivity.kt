package com.laivinieks.aleatorya_clashofcards

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.FeatureGameDeskNavHost

import com.laivinieks.aleatorya_clashofcards.ui.theme.Aleatorya_ClashOfCardsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Aleatorya_ClashOfCardsTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                   FeatureGameDeskNavHost()
                }
            }
        }
    }
}
