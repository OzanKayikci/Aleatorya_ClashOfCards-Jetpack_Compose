package com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.requiredWidthIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.model.Card
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.util.Characters

@Composable
fun MiniCardDeck(modifier: Modifier = Modifier, cards: List<Card>, currentCardIndex: Int = 0) {

    Box(
        modifier = modifier
            .requiredHeightIn(min = 60.dp)
            .padding(16.dp),
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
                        .offset(y = (-10).dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(Color.Cyan.copy(0.5f))
                } else {
                    modifier
                        .rotate(rotateVal)
                        .padding(horizontal = (5 * index).dp)

                }

            ) {
                CardItem(
                    card = card, sizeMultiplier = if (currentCardIndex == index) 0.38f else 0.35f
                )

            }
        }


    }
}


@Preview(showSystemUi = true)
@Composable
fun CardDeckPreview() {
    val cards = Characters.fighters.subList(0, 8)
    MiniCardDeck(cards = cards)
}