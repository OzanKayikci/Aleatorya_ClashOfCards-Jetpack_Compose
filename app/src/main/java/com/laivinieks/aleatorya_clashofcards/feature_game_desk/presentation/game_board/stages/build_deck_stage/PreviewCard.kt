package com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.game_board.stages.build_deck_stage

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.model.Card
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.components.CardItem

@Composable
fun PreviewCard(
    selectedCard:Card,
    onDismissRequest: () -> Unit
) {
    var offsetX by remember { mutableFloatStateOf(0f) }
    var offsetY by remember { mutableFloatStateOf(0f) }
    var scale by remember { mutableFloatStateOf(3f) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                onDismissRequest()
            },
        contentAlignment = Alignment.Center
    ) {


        Box(
            modifier = Modifier
                .fillMaxWidth(1f)
              ,
            contentAlignment = Alignment.Center
        ) {
           CardItem(card =selectedCard, sizeMultiplier = 4.5f, isClickable = false)

        }

    }
}