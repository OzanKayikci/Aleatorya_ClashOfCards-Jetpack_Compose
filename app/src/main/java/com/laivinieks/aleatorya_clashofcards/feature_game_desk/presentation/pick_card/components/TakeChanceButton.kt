package com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.pick_card.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.laivinieks.aleatorya_clashofcards.R
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.components.bounceClick

@Composable
fun TakeChanceButton(modifier: Modifier = Modifier, clicked: () -> Unit) {
    Box(modifier = modifier.bounceClick(onAnimationFinished = {
        clicked()
    })) {
        Image(painter = painterResource(id = R.drawable.burned_dice_icon), contentDescription = "take change")
    }
}

