package com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.pick_card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.model.Card
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.components.CardItem

@Composable
fun MiniCardDeck(
    modifier: Modifier = Modifier,
    cards: List<Card>,
    currentCardIndex: Int = 0,
    onClick: (Boolean) -> Unit
) {

    Box(
        modifier = modifier
            .requiredHeightIn(min = 60.dp),
        contentAlignment = Alignment.Center
    ) {

        Box(
            modifier = modifier
                .requiredHeightIn(min = 60.dp)
                .padding(start = 36.dp)

        ) {
            for (item in cards.withIndex()) {
                val index = item.index
                val card = item.value
                val rotateVal = (-25 + (10 * index)).toFloat()

                Box(
                    if (currentCardIndex == index) {
                        modifier
                            .rotate(rotateVal)
                            .padding(horizontal = (5 * index).dp)
                            .offset(y = (-20).dp)
                            .clip(RoundedCornerShape(3.dp))
                            .background(Color.Cyan.copy(0.5f))
                    } else {
                        modifier
                            .rotate(rotateVal)
                            .padding(horizontal = (5 * index).dp)

                    }

                ) {
                    CardItem(
                        card = card, isClickable = false, sizeMultiplier = if (currentCardIndex == index) 0.9f else 0.80f
                    )

                }
            }

        }
        Box(modifier = modifier
            .width(150.dp)
            .height(120.dp)
            .clickable {
                onClick(true)
            }) {}

    }
}


//@Preview(showSystemUi = true)
//@Composable
//private fun CardDeckPreview() {
//    val cards = Characters.fighters.subList(0, 8)
//    MiniCardDeck(cards = cards)
//}