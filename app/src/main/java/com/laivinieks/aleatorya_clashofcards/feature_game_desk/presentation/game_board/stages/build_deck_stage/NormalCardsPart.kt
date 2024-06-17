package com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.game_board.stages.build_deck_stage

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.model.Card
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.components.CardItem

@Composable
fun NormalCardsPart(
    modifier: Modifier = Modifier,
    cards: List<Card> = emptyList(),
    showNewCard: Boolean = false,
    newCardId: Int = 0,
    cardForPreview: (Card) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        //contentPadding = PaddingValues(horizontal = 32.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)

    ) {
        itemsIndexed(cards) { index, card ->
            key(index) {
                AnimatedVisibility(
                    modifier = Modifier.padding(vertical = 2.dp),
                    visible = showNewCard || newCardId != card.id,
                    enter = fadeIn() + scaleIn(animationSpec = tween(500)),
                    exit = fadeOut() + scaleOut(animationSpec = tween(500))
                ) {
                    CardItem(
                        card = card, sizeMultiplier = 1f, clickedForPreview = cardForPreview
                    )
                }
            }
        }
    }
}