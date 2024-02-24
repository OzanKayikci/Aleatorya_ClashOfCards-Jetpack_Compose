package com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.dice_roller


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun DiceRoller(modifier: Modifier = Modifier, items: List<Int>, isRolling: Boolean = false, selected: (Int) -> Unit) {
    var rolling by remember { mutableStateOf(isRolling) }
    Log.d("isr", isRolling.toString())
    LaunchedEffect(isRolling) {
        rolling = isRolling // isRolling değeri değiştiğinde rolling'e atama yapılıyor
        if (rolling) {
            delay(3600)
            rolling = false
        }
    }
    Column(
        modifier = modifier
        ) {
        AnimatedDice(rolling, items = items, selected = selected)

    }
}

